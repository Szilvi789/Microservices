package com.example.microservices.order_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "orders")
public class Order {
    @Id
    private String id;
    private String orderNumber;
    private String skuCode;
    private BigDecimal totalPrice;
    private Integer quantity;

    public Order() {
    }

    public Order(String orderNumber, String id, String skuCode, BigDecimal totalPrice, Integer quantity) {
        this.orderNumber = orderNumber;
        this.id = id;
        this.skuCode = skuCode;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordeNumber='" + orderNumber + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", totalPrice=" + totalPrice +
                ", quantity=" + quantity +
                '}';
    }
}
