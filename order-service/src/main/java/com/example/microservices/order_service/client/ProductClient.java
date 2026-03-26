package com.example.microservices.order_service.client;

import com.example.microservices.order_service.dto.OrderProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/api/product/getProductDetails")
    OrderProductResponse getProductDetails(@RequestParam("skuCode") String skuCode);

}
