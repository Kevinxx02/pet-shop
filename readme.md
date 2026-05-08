# PetShop Catalog API

Backend-oriented PetShop catalog system built with Java and Spring Boot.

Designed using modern backend architecture practices focused on scalability, maintainability and transactional consistency.

---

<h2>Technical Highlights</h2>

<ul>
  <li>DDD-inspired layered architecture</li>
  <li>CQRS separation between commands and queries</li>
  <li>Transactional Outbox Pattern</li>
  <li>Asynchronous messaging with RabbitMQ</li>
  <li>JWT stateless authentication</li>
  <li>JPA persistence isolated from domain layer</li>
  <li>Unit and integration testing</li>
  <li>Dockerized development environment</li>
  <li>ACID transaction management</li>
</ul>

---

<h2>Technology Stack</h2>

<h3>Backend</h3>

<ul>
  <li>Java 17</li>
  <li>Spring Boot 3</li>
  <li>Spring MVC</li>
  <li>Spring Security</li>
  <li>Spring Data JPA (Hibernate)</li>
</ul>

<h3>Architecture</h3>

<ul>
  <li>DDD (Domain-Driven Design)</li>
  <li>CQRS</li>
  <li>Outbox Pattern</li>
</ul>

<h3>Persistence</h3>

<ul>
  <li>PostgreSQL</li>
  <li>Flyway migrations</li>
</ul>

<h3>Messaging</h3>

<ul>
  <li>RabbitMQ</li>
</ul>

<h3>Testing</h3>

<ul>
  <li>JUnit 5</li>
  <li>Mockito</li>
  <li>Integration Testing</li>
</ul>

<h3>Infrastructure & Tooling</h3>

<ul>
  <li>Docker</li>
  <li>Docker Compose</li>
  <li>Maven</li>
</ul>

---

<h2>Architecture Overview</h2>

The project follows a layered architecture inspired by Domain-Driven Design, with strict separation between business domain and infrastructure concerns.

```text
Client
   ↓
REST Controllers
   ↓
Application Layer
   ↓
Domain Layer
   ↓
Persistence + Outbox
   ↓
RabbitMQ
```

---

<h2>Project Structure</h2>

```text
Domain
├── user
│   ├── User
│   ├── HashedPassword
│   ├── UserRepository
│   └── UserReadRepository
├── product
├── category
└── shared
    └── Email

Application
├── user
│   ├── AuthUserService
│   ├── CreateUserService
│   ├── UserView
│   ├── RefreshRequest
│   └── UserMapper
├── product
└── services

Infrastructure
├── persistence
│   ├── JpaUserRepository
│   ├── JpaUserReadRepository
│   ├── SpringDataUserRepository
│   ├── SpringDataUserReadRepository
│   ├── UserJpaEntity
│   └── UserMapper
├── security
├── messaging
├── outboxworker
└── config

Web
├── BaseResponse
├── GlobalExceptionHandler
├── WebConfig
└── user
    ├── UserController
    └── UserCreateRequest
```

---

<h2>Persistence Architecture</h2>

The domain model is fully isolated from JPA persistence implementation.

<h3>Principles</h3>

<ul>
  <li>Domain layer has no dependency on JPA</li>
  <li>Persistence models exist only in infrastructure layer</li>
  <li>Explicit mappers translate between domain and persistence models</li>
</ul>

<h3>Data Flow</h3>

```text
Domain
   ↓
Mapper
   ↓
JPA Entity
   ↓
Repository
   ↓
Database
```

---

<h2>CQRS Approach</h2>

The system applies lightweight CQRS separation.

<h3>Command Side</h3>

<ul>
  <li>User registration</li>
  <li>Authentication</li>
  <li>Product creation</li>
  <li>Domain event generation</li>
  <li>Transactional operations</li>
</ul>

<h3>Query Side</h3>

<ul>
  <li>Read repositories</li>
  <li>Optimized queries</li>
  <li>Separated read models when required</li>
</ul>

---

<h2>Transactional Consistency</h2>

Application services coordinate transactional consistency between persistence and messaging.

<h3>Example Flow</h3>

```text
Create Product
   ↓
Persist Entity
   ↓
Generate Domain Event
   ↓
Persist Outbox Event
   ↓
Commit Transaction
```

This guarantees:

<ul>
  <li>Atomicity between domain persistence and outbox events</li>
  <li>Eventual consistency with RabbitMQ</li>
  <li>Reliable event publication</li>
</ul>

---

<h2>Authentication & Security</h2>

JWT-based stateless authentication implemented with Spring Security.

<h3>Features</h3>

<ul>
  <li>Access Token</li>
  <li>Refresh Token</li>
  <li>Role-based authorization</li>
  <li>Custom JWT filters</li>
  <li>Stateless authentication flow</li>
</ul>

<h3>Authentication Example</h3>

```java
Authentication auth = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(email, password)
);
```

---

<h2>Outbox Pattern</h2>

The system implements the Transactional Outbox Pattern to ensure consistency between database state and asynchronous messaging.

<h3>Flow</h3>

```text
Domain Event Generated
        ↓
Persisted in outbox_events table
        ↓
Worker processes pending events
        ↓
Published to RabbitMQ
        ↓
Event marked as SENT
```

<h3>Event States</h3>

<ul>
  <li>PENDING</li>
  <li>PROCESSING</li>
  <li>SENT</li>
  <li>FAILED</li>
</ul>

---

<h2>Outbox Worker</h2>

Background scheduled worker responsible for event delivery.

```java
@Scheduled(fixedDelay = 5000)
```

<h3>Responsibilities</h3>

<ul>
  <li>Read pending events</li>
  <li>Concurrency-safe locking</li>
  <li>RabbitMQ publication</li>
  <li>Update event status</li>
</ul>

---

<h2>RabbitMQ Integration</h2>

Asynchronous communication based on exchanges and routing keys.

<h3>Configuration</h3>

<ul>
  <li>Main Exchange: <code>petshop.exchange</code></li>
  <li>Routing Key: <code>product.created</code></li>
</ul>

<h3>Publishing Example</h3>

```java
rabbitTemplate.convertAndSend(
    exchange,
    routingKey,
    payload
);
```

---

<h2>Testing Strategy</h2>

The project includes both unit and integration testing approaches.

<h3>Unit Testing</h3>

<ul>
  <li>JUnit 5</li>
  <li>Mockito</li>
  <li>Service layer validation</li>
  <li>Domain behavior testing</li>
</ul>

<h3>Integration Testing</h3>

<ul>
  <li>PostgreSQL real database testing in isolated containerized environment</li>
  <li>Repository integration validation</li>
  <li>Outbox flow testing</li>
  <li>Consumer testing</li>
  <li>Transactional consistency validation</li>
</ul>

<h3>Messaging Testing</h3>

<ul>
  <li>RabbitMQ interactions mocked during integration testing</li>
  <li>Event publication validation</li>
  <li>Consumer flow validation</li>
</ul>

---

<h2>Dockerized Environment</h2>

The project includes a fully containerized local environment.

<h3>Services</h3>

<ul>
  <li>Spring Boot API</li>
  <li>PostgreSQL</li>
  <li>RabbitMQ</li>
</ul>

<h3>Run Locally</h3>

```bash
docker compose up --build
```

---

<h2>Current Features</h2>

<ul>
  <li>JWT Authentication</li>
  <li>Refresh Tokens</li>
  <li>Products & Categories</li>
  <li>DDD-inspired architecture</li>
  <li>CQRS separation</li>
  <li>Transactional Outbox Pattern</li>
  <li>RabbitMQ integration</li>
  <li>Background workers</li>
  <li>Unit Testing</li>
  <li>Integration Testing</li>
  <li>Dockerized infrastructure</li>
</ul>

---

<h2>Future Improvements</h2>

<ul>
  <li>Testcontainers integration</li>
  <li>CI/CD pipeline</li>
  <li>Distributed tracing</li>
  <li>Centralized logging</li>
  <li>Dead-letter queues</li>
  <li>Retry policies</li>
  <li>Observability & metrics</li>
  <li>API rate limiting</li>
</ul>