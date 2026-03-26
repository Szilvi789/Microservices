package com.example.microservices.product_service.repository;

import com.example.microservices.product_service.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    boolean existsBySkuCodeAndQuantityGreaterThanEqual(String skuCode, Integer quantity);
    Optional<Inventory> findBySkuCode(String skuCode);
}
