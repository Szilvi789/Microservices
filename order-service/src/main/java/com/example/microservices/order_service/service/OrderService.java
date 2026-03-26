package com.example.microservices.order_service.service;

import com.example.microservices.order_service.client.InventoryClient;
import com.example.microservices.order_service.client.ProductClient;
import com.example.microservices.order_service.dto.OrderInventoryRequest;
import com.example.microservices.order_service.dto.OrderProductResponse;
import com.example.microservices.order_service.dto.OrderResponse;
import com.example.microservices.order_service.model.Order;
import com.example.microservices.order_service.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final ProductClient productClient;

    public OrderService(OrderRepository orderRepository, InventoryClient inventoryClient, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.inventoryClient = inventoryClient;
        this.productClient = productClient;
    }

    @Transactional
    public OrderResponse placeOrder(OrderInventoryRequest orderInventoryRequest) {
        // ✅ Step 1: Check inventory BEFORE placing the order
        boolean isProductInStock = inventoryClient.isInStock(orderInventoryRequest.skuCode(), orderInventoryRequest.quantity());

        if (!isProductInStock) {
            throw new RuntimeException("Product with skuCode " + orderInventoryRequest.skuCode() + " is out of stock");
        }

        // ✅ Step 2: Fetch Product Details from Product Service
        OrderProductResponse productDetails = productClient.getProductDetails(orderInventoryRequest.skuCode());

        if (productDetails == null) {
            throw new RuntimeException("Product with skuCode " + orderInventoryRequest.skuCode() + " not found in Product Service");
        }

        // ✅ Step 3: Calculate the Total Price
        BigDecimal totalPrice = productDetails.price().multiply(BigDecimal.valueOf(orderInventoryRequest.quantity()));

        // ✅ Step 4: Create order object and save it
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setSkuCode(orderInventoryRequest.skuCode());
        order.setQuantity(orderInventoryRequest.quantity());
        order.setTotalPrice(totalPrice);

        orderRepository.save(order);
        log.info("Order placed: {}", order);

        // ✅ Step 4: Reduce inventory AFTER order is successfully placed
        inventoryClient.reduceStock(orderInventoryRequest.skuCode(), orderInventoryRequest.quantity());

        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getTotalPrice(), order.getQuantity());
    }

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getTotalPrice(), order.getQuantity()))
                .toList();
    }
}