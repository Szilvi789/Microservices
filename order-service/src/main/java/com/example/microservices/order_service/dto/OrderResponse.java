package com.example.microservices.order_service.dto;

import java.math.BigDecimal;
// this is resposnse for getAllOrders Controller
public record OrderResponse(String id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}
