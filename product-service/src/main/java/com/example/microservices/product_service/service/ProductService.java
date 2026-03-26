package com.example.microservices.product_service.service;

import com.example.microservices.product_service.dto.ProductRequest;
import com.example.microservices.product_service.dto.ProductResponse;
import com.example.microservices.product_service.model.Product;
import com.example.microservices.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        product.setSkuCode(productRequest.skuCode());
        productRepository.save(product);
        log.info("Product created: {}", product);
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSkuCode());
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSkuCode()))
                .toList();
    }

    public ProductResponse getProductDetails(String skuCode) {
        Product product = productRepository.findBySkuCode(skuCode)
                .orElseThrow(() -> new RuntimeException("Product with skuCode " + skuCode + " not found"));
        return new ProductResponse(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getSkuCode());
    }
}
