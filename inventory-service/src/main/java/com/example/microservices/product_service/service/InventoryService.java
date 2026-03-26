package com.example.microservices.product_service.service;

import com.example.microservices.product_service.dto.InventoryRequest;
import com.example.microservices.product_service.dto.InventoryResponse;
import com.example.microservices.product_service.model.Inventory;
import com.example.microservices.product_service.repository.InventoryRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    public final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean isInStock(String skuCode, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);
        return inventory.isPresent() && inventory.get().getQuantity() >= quantity;
    }

    public InventoryResponse saveInventory(InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());
        inventoryRepository.save(inventory);
        log.info("Inventory created: {}", inventory);
        return new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity());
    }

    public void reduceStock(String skuCode, Integer quantity) {
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Product with skuCode " + skuCode + " not found in inventory"));

        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available for " + skuCode);
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

    public List<InventoryResponse> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventory -> new InventoryResponse(inventory.getId(), inventory.getSkuCode(), inventory.getQuantity()))
                .toList();
    }
}