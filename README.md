# Digital Banking System

A Java and Spring Boot-based digital banking backend built with a microservices architecture. The system is designed to support account management, bank transfers, transaction history, fraud detection, payment processing, and notifications, with Kafka enabling event-driven communication between services.

> This project is currently under development and is approximately **55% complete**. This estimate is based on implemented business logic, service integration, build status, testing, and deployment readiness.

## Development Progress

```text
███████████░░░░░░░░░ 55%
```

| Module | Current Status |
| --- | --- |
| Account Service | In progress: includes account creation, account lookup, balance deduction, crediting, and account blocking endpoints |
| Transaction Service | In progress: includes transfers, transaction lookup, transaction history, and the foundation of Saga event handling |
| Fraud Detection Service | In progress: transaction risk checks and Kafka fraud events are being implemented |
| Payment Service | In progress: payment request, response, entity, and status models have been created |
| Notification Service | In progress: an OTP Kafka consumer has been started, but notification delivery is not implemented yet |
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

## How Kafka Is Used

Apache Kafka is used as the event broker between the microservices. It allows each service to react to transaction events asynchronously without being tightly coupled to the other services. Kafka also supports the event-driven Saga flow used to coordinate transfers, fraud checks, account updates, and notifications.

The current transaction flow is:

1. **Transaction Service** creates a transfer with the `PROCESSING` status and publishes a `transaction.initiated` event.
2. **Fraud Detection Service** consumes `transaction.initiated`, checks the transaction, and publishes either `verification.required` or `fraud.check.clean`.
3. **Transaction Service** consumes `verification.required`, generates an OTP, stores it temporarily in Redis, and changes the transaction status to `PENDING_VERIFICATION`.
4. **Account Service** is prepared to consume `transaction.completed` to credit the receiver's account.
5. **Account Service** also consumes `fraud.detected` to block an account flagged for fraudulent activity.

| Kafka Topic | Producer | Consumer | Purpose |
| --- | --- | --- | --- |
| `transaction.initiated` | Transaction Service | Fraud Detection Service | Starts the fraud-checking process for a new transfer |
| `verification.required` | Fraud Detection Service | Transaction Service | Requests OTP verification for a suspicious transfer |
| `transaction.otp.generated` | Transaction Service (in progress) | Notification Service | Delivers generated OTP event data to the notification workflow |
| `fraud.check.clean` | Fraud Detection Service | Transaction Service (in progress) | Reports that a transfer passed the fraud checks |
| `transaction.completed` | Transaction Service (in progress) | Account Service | Credits the receiver after a successful transfer |
| `fraud.detected` | Fraud workflow (in progress) | Account Service | Blocks an account associated with confirmed fraud |
| `transaction.refunded` | Transaction Service (planned) | Relevant services (planned) | Supports compensation when a transfer fails |

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
