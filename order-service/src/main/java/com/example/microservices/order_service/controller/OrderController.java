package com.example.microservices.order_service.controller;

import com.example.microservices.order_service.dto.OrderInventoryRequest;
import com.example.microservices.order_service.dto.OrderResponse;
import com.example.microservices.order_service.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderInventoryRequest orderInventoryRequest) {
        return orderService.placeOrder(orderInventoryRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }


}
