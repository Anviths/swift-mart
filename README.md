SwiftMart – Microservices-Based Grocery Delivery Platform

SwiftMart is a scalable, event-driven grocery delivery backend inspired by Blinkit.
It is designed using Spring Boot microservices, Kafka for asynchronous communication, JWT-based security, and Dockerized deployments, focusing on speed, reliability, and real-world backend architecture.

This project demonstrates production-grade backend engineering practices expected from mid–senior backend engineers.

🎯 What This Project Demonstrates

Microservices architecture & service separation

Stateless authentication using JWT & refresh Token

Asynchronous communication using Kafka

Dockerized services with centralized configuration

Clean REST API design & database modeling

Real-world order, payment, and delivery workflows



🏗️ System Architecture
Microservices Overview
Service	Responsibility
API Gateway	Entry point, JWT validation, request routing
Auth Service	User authentication & JWT generation
User Service	User profile & account management
Product Service	Product & category management
Order Service	Order creation & lifecycle
Payment Service	Payment processing (Kafka consumer)
Delivery Service	Delivery assignment & tracking


🔁 Event-Driven Flow (Kafka)

SwiftMart uses Apache Kafka to decouple services and handle asynchronous workflows.

Order Processing Flow
User places order
→ Order Service creates order
→ Publishes ORDER_CREATED event (Kafka)
→ Payment Service consumes event
→ Payment processed
→ Publishes PAYMENT_COMPLETED event
→ Order status updated


This ensures:

Loose coupling

Better scalability

Fault tolerance

Real-world backend design maturity

🔐 Security Architecture

Spring Security + JWT

Stateless authentication

Role-based access control:

USER

ADMIN

DELIVERY_AGENT

JWT validated at API Gateway level

Secured inter-service communication

✨ Core Features
Authentication & User Management

User registration & login

JWT-based authentication

Profile view & update

Role-based authorization

Product & Category Management

Browse products by category

Product details view

Admin APIs for product & category CRUD

Cart & Order Management

Add/remove items from cart

Convert cart to order

View order history

Track order status

Payment Processing

Kafka-based async payment handling

Payment status linked to orders

Transaction persistence

Delivery Tracking

Assign delivery agents

Update delivery status

Real-time order tracking

🛠️ Tech Stack
Backend

Java 17

Spring Boot

Spring Security (JWT)

Spring Cloud (Gateway)

Hibernate / JPA

Apache Kafka

Database

PostgreSQL

DevOps

Docker

Docker Compose

Frontend

React

Redux

Axios

🐳 Dockerized Setup

Each microservice runs in its own Docker container.

Services via Docker Compose

API Gateway

Auth Service

User Service

Product Service

Order Service

Payment Service

Delivery Service

Kafka + Zookeeper

PostgreSQL

Run Entire System
docker-compose up --build


All services will be available through the API Gateway.

🔗 API Gateway Routing (Sample)
Route	Service
/auth/**	Auth Service
/users/**	User Service
/products/**	Product Service
/orders/**	Order Service
/payments/**	Payment Service
/deliveries/**	Delivery Service
⚙️ Local Development (Without Docker)
Prerequisites

Java 17+

PostgreSQL

Kafka

Node.js

Backend
./mvnw spring-boot:run

Frontend
npm install
npm start

📁 Repository Structure
swiftmart/
├── api-gateway/
├── auth-service/
├── user-service/
├── product-service/
├── order-service/
├── payment-service/
├── delivery-service/
├── docker-compose.yml
└── README.md

🧪 Testing

JUnit & Mockito

Service-layer unit tests

Integration tests for core flows

🚧 Future Enhancements

Redis caching

Circuit breaker (Resilience4j)

Distributed tracing

AWS deployment (EC2 + RDS)

CI/CD pipeline (GitHub Actions / Jenkins)
