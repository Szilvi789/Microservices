package com.example.microservices.order_service.dto;

import java.math.BigDecimal;
//this is response for Product client
public record OrderProductResponse(String id, String name, String description, BigDecimal price) {

}
