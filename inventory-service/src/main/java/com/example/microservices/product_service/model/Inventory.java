package com.example.microservices.product_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "inventory")
public class Inventory {
    @Id
    private String id;
    private String skuCode;
    private Integer quantity;

    public Inventory() {
    }

    public Inventory(String id, String skuCode, Integer quantity) {
        this.id = id;
        this.skuCode = skuCode;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id='" + id + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
