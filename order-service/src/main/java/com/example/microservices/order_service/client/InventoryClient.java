package com.example.microservices.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
    @GetMapping("/api/inventory/check")
    boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);

    @PutMapping("/api/inventory/reduce")
    void reduceStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity);
}
