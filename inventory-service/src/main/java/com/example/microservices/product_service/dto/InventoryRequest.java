package com.example.microservices.product_service.dto;

public record InventoryRequest(String id, String skuCode, Integer quantity) {
}
