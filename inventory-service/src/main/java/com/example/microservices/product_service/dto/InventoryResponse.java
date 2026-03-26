package com.example.microservices.product_service.dto;

public record InventoryResponse(String id, String skuCode, Integer quantity) {
}
