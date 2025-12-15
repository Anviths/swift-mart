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



