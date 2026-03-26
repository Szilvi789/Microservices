package com.example.microservices.order_service;

import com.example.microservices.order_service.client.InventoryClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
@EnableFeignClients(clients = InventoryClient.class)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MongoDBContainer mongoDbContainer() {
		return new MongoDBContainer(DockerImageName.parse("mongo:latest"));
	}

    @Bean
    @Primary
    public InventoryClient inventoryClient() {
        return new InventoryClient() {
            @Override
            public boolean isInStock(String skuCode, Integer quantity) {
                return true; // Mock response for testing
            }

            @Override
            public void reduceStock(String skuCode, Integer quantity) {

            }
        };
    }
}

