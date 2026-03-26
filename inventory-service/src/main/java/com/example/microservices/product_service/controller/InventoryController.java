package com.example.microservices.product_service.controller;

import com.example.microservices.product_service.dto.InventoryRequest;
import com.example.microservices.product_service.dto.InventoryResponse;
import com.example.microservices.product_service.model.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.microservices.product_service.service.InventoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse saveInventory(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.saveInventory(inventoryRequest);
    }

    //check if specific items are available in the inventory based on their skuCode and quantity.
    //returns true if the items are available aka skuCOde exists && quantity is greater than or equal to the requested quantity
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }

    @PutMapping("/reduce")
    @ResponseStatus(HttpStatus.OK)
    public void reduceStock(@RequestParam("skuCode") String skuCode, @RequestParam("quantity") Integer quantity) {
        inventoryService.reduceStock(skuCode, quantity);
    }



}
