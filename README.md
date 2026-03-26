# Microservices Practice Project

This repository is a small Spring Boot microservices practice project.

I built it to practice splitting application logic into multiple services instead of keeping everything in one monolith. The main goal was to understand service-to-service communication, service discovery, separate data stores, and local development setup for a microservices-style backend.

## Project Overview

The project is organized into several small backend applications:

- `product-service`
- `inventory-service`
- `order-service`
- `eurekaServer`

Together, they simulate a simple product ordering flow:

1. products are created and stored in the product service
2. stock levels are managed in the inventory service
3. orders are created in the order service
4. the order service communicates with the other services to check stock and fetch product details
5. Eureka is used for service registration and discovery

This is a small learning project, not a production-ready microservices platform. The purpose was to practice architecture concepts and communication between independent Spring Boot services.

## Purpose And Practice Focus

I created this project to practice:

- building multiple Spring Boot applications that work together
- understanding service boundaries
- using service discovery with Eureka
- service-to-service calls with OpenFeign
- using MongoDB with separate services
- running supporting infrastructure with Docker Compose
- writing basic tests with Testcontainers and Rest Assured

## Services

### 1. Product Service

The `product-service` is responsible for product data.

Main responsibilities:

- create products
- list all products
- fetch product details by `skuCode`

Default port:

- `8080`

Main endpoint group:

- `/api/product`

### 2. Inventory Service

The `inventory-service` is responsible for stock management.

Main responsibilities:

- create inventory entries
- list inventory records
- check whether a product is in stock
- reduce stock after an order is placed

Default port:

- `8082`

Main endpoint group:

- `/api/inventory`

### 3. Order Service

The `order-service` coordinates the ordering flow.

Main responsibilities:

- create orders
- list all orders
- call the inventory service to check stock
- call the product service to fetch product details
- calculate the total order price
- reduce stock after a successful order

Default port:

- `8081`

Main endpoint group:

- `/api/order`

### 4. Eureka Server

The `eurekaServer` acts as the service registry for the project.

Default port:

- `8761`

Its purpose is to let the services register themselves and discover each other by service name.

## Technologies Used

This project uses the following technologies:

- Java 21 for the microservices
- Java 17 for the Eureka server
- Spring Boot 3.4
- Spring Cloud 2024.0.0
- Spring Web
- Spring Data MongoDB
- Spring Cloud Netflix Eureka
- Spring Cloud OpenFeign
- Spring Cloud LoadBalancer
- MongoDB
- Maven
- Docker Compose
- Lombok
- JUnit 5
- Testcontainers
- Rest Assured

## Architecture Notes

This project follows a simple microservices structure:

- each service is its own Spring Boot application
- each business area is separated into its own codebase
- service registration is handled by Eureka
- service-to-service HTTP communication is handled with Feign clients
- each service is configured with its own MongoDB database connection

The main interaction happens in the `order-service`, which calls:

- `inventory-service` to check and reduce stock
- `product-service` to fetch product information

## Local Configuration

The services are configured with the following default ports:

- `product-service`: `8080`
- `order-service`: `8081`
- `inventory-service`: `8082`
- `eurekaServer`: `8761`

MongoDB ports used in the project:

- product database: `27017`
- order database: `27018`
- inventory database: `27019`

## Docker Setup

This repository includes Docker Compose files for local infrastructure:

- `eurekaServer/docker-compose.yml` starts Eureka together with MongoDB containers
- `order-service/docker-compose.yml` starts the order MongoDB container

These files show how I practiced running supporting services locally while working on the microservices.

## Testing

The project includes basic testing support with:

- Testcontainers for MongoDB-backed tests
- Rest Assured for API testing
- Spring Boot test support

The tests are especially visible in:

- `product-service`
- `order-service`

## What This Project Demonstrates

Even though this is a small practice project, it demonstrates:

- microservices thinking instead of a single application structure
- basic service discovery
- inter-service communication with Feign
- separating product, inventory, and order responsibilities
- working with multiple Spring Boot applications in parallel
- combining application code with local container setup

## Notes

- This repository is focused on backend and infrastructure practice. It does not include a frontend application.
- The folder name is currently `Microsevices`, which is a typo from local development, but the project itself is a microservices practice repository.

## Author

Szilvia Varga

Practice project for learning Spring Boot microservices, service discovery, and service-to-service communication.
