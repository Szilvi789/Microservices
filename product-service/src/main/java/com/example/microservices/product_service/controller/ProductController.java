package com.example.microservices.product_service.controller;

import com.example.microservices.product_service.dto.ProductRequest;
import com.example.microservices.product_service.dto.ProductResponse;
import com.example.microservices.product_service.model.Product;
import com.example.microservices.product_service.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/getProductDetails")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductDetails(@RequestParam String skuCode) {
        return productService.getProductDetails(skuCode);
    }
}
