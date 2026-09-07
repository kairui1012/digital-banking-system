# Digital Banking System

A Java and Spring Boot-based digital banking backend built with a microservices architecture. The system is designed to support account management, bank transfers, transaction history, fraud detection, payment processing, and notifications, with Kafka enabling event-driven communication between services.

> This project is currently under development and is approximately **50% complete**.

## Development Progress

```text
██████████░░░░░░░░░░ 50%
```

| Module | Current Status |
| --- | --- |
| Account Service | In progress: includes account creation, account lookup, balance deduction, crediting, and account blocking endpoints |
| Transaction Service | In progress: includes transfers, transaction lookup, transaction history, and the foundation of Saga event handling |
| Fraud Detection Service | In progress: transaction risk checks and Kafka fraud events are being implemented |
| Payment Service | Planned: the basic Spring Boot service structure has been created |
| Notification Service | Planned: the basic Kafka and email notification dependencies have been configured |
| API Gateway | Planned: the basic Spring Cloud Gateway structure has been created |

## Technology Stack

- Java 17
- Spring Boot
- Spring Cloud Gateway / OpenFeign
- Spring Data JPA
- Apache Kafka
- MySQL
- Redis
- Maven
- Docker Compose
- Lombok

## Project Structure

```text
digital-banking-system/
├── account-service/          # Bank account and balance management
├── transaction-service/      # Transfers and transaction history
├── fraud-detection-service/  # Transaction risk and fraud detection
├── payment-service/          # Payment processing
├── notification-service/     # Email and event notifications
├── api-gateway/              # Unified API entry point
└── docker-compose.yml        # MySQL, Redis, Kafka, and Zookeeper
```

## Currently Implemented

- Account creation, balance lookup, balance deduction, crediting, and account blocking
- Transfer creation and transaction status persistence
- OpenFeign communication between Account Service and Transaction Service
- Foundation for Kafka-based transaction, completion, and fraud detection events
- MySQL data persistence configuration
- Docker Compose development environment for Redis, Kafka, Zookeeper, and MySQL

## Roadmap

- Complete the fraud detection rules and suspicious transaction verification flow
- Complete Saga compensation and failed-transfer refund handling
- Implement the Payment Service business logic
- Implement email and transaction notifications
- Configure and connect API Gateway routes
- Add authentication, authorization, and API security
- Add unit tests, integration tests, and API documentation
- Complete the containerized deployment configuration

## Starting the Infrastructure

Install Docker, then run the following command from the project root:

```bash
docker compose up -d
```

Each microservice can be started from its own directory with the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Before starting the services, verify the MySQL, Kafka, and Redis connection settings for your local environment.

