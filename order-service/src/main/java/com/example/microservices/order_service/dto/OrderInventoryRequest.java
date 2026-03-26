package com.example.microservices.order_service.dto;
// this is request for InventoryController
public record OrderInventoryRequest(String id, String skuCode, Integer quantity) {

}
