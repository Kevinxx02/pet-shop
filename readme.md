<h1>PetShop Catalog API</h1>

<p align="center">
    <strong>
        Distributed backend-oriented PetShop platform built with 
        Java and Spring Boot.
    </strong> 
</p>

<p align="center">
    Designed using modern backend architecture patterns focused on
    scalability, transactional consistency, observability and
    asynchronous processing
</p>

## Architecture Overview
<p align="center"> <img src="https://img.shields.io/badge/Java-17-orange" /> <img src="https://img.shields.io/badge/Spring_Boot-3-green" /> <img src="https://img.shields.io/badge/PostgreSQL-16-blue" /> <img src="https://img.shields.io/badge/RabbitMQ-Async%20Messaging-ff6600" /> <img src="https://img.shields.io/badge/Kafka-Event%20Streaming-black" /> <img src="https://img.shields.io/badge/Kubernetes-Orchestration-326ce5" /> <img src="https://img.shields.io/badge/Testcontainers-Integration%20Testing-2496ed" /> </p>

## Technical Highlights
<ul>
  <li>DDD-inspired layered architecture</li>
  <li>CQRS separation between commands and queries</li>
  <li>Transactional Outbox Pattern</li>
  <li>RabbitMQ asynchronous messaging</li>
  <li>Kafka event streaming integration</li>
  <li>Structured JSON logging</li>
  <li>Micrometer metrics instrumentation</li>
  <li>JWT stateless authentication</li>
  <li>Rate limiting support</li>
  <li>Integration testing with Testcontainers</li>
  <li>Dockerized infrastructure</li>
  <li>Kubernetes deployment support</li>
  <li>Transactional consistency guarantees</li>
</ul>

---
## Technology Stack

<h3>Backend</h3>
<ul>
    <li>Java 17</li>
    <li>Spring Boot 3</li>
    <li>Spring MVC</li>
    <li>Spring Security</li>
    <li>Spring Data JPA</li>
    <li>Hibernate</li>
    <li>Micrometer</li>
</ul>
    <h3>Architecture</h3>
<ul>
    <li>Domain-Driven Design (DDD)</li>
    <li>CQRS</li>
    <li>Transactional Outbox Pattern</li>
    <li>Event-Driven Architecture</li>
    <li>Layered Architecture</li>
</ul>
    <h3>Persistence</h3>
<ul>
    <li>PostgreSQL</li>
    <li>Flyway Migrations</li>
</ul>
<h2>Messaging & Streaming</h2>

<h3>RabbitMQ</h3>
<p>
  Used for asynchronous business messaging and queue-based processing.
</p>

<h3>Kafka</h3>
<p>
  Used for event streaming and analytics-oriented event pipelines.
</p>

<hr>

<h2>Testing</h2>

<ul>
  <li>JUnit 5</li>
  <li>Mockito</li>
  <li>Spring Boot Test</li>
  <li>Integration Testing</li>
  <li>Testcontainers</li>
</ul>

<hr>

<h2>Infrastructure & Tooling</h2>

<ul>
  <li>Docker</li>
  <li>Docker Compose</li>
  <li>Kubernetes</li>
  <li>Minikube</li>
  <li>Maven</li>
</ul>

<hr>

<h2>CI/CD</h2>

<ul>
  <li>GitHub Actions</li>
  <li>Automated Maven test pipeline</li>
  <li>Docker image build automation</li>
  <li>Docker Hub publishing</li>
  <li>Integration test execution</li>
  <li>Commit SHA tagging strategy</li>
</ul>

<hr>

<h2>High-Level Architecture</h2>

<pre>
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
RabbitMQ + Kafka
</pre>

<hr>

<h2>Layered Architecture</h2>

<h3>Domain Layer</h3>

<p>
  Contains pure business rules and domain models.
</p>

<h4>Principles</h4>

<ul>
  <li>No dependency on frameworks</li>
  <li>No dependency on JPA</li>
  <li>Rich domain modeling</li>
  <li>Explicit repository abstractions</li>
</ul>

<hr>

<h3>Application Layer</h3>

<p>
  Coordinates use cases and transactional boundaries.
</p>

<h4>Responsibilities</h4>

<ul>
  <li>Authentication</li>
  <li>Product creation</li>
  <li>Transaction orchestration</li>
  <li>Domain event generation</li>
  <li>CQRS coordination</li>
</ul>

<hr>

<h3>Infrastructure Layer</h3>

<p>
  Contains technical implementations.
</p>

<h4>Includes</h4>

<ul>
  <li>JPA repositories</li>
  <li>Kafka producers/consumers</li>
  <li>RabbitMQ publishers</li>
  <li>Outbox workers</li>
  <li>Security configuration</li>
  <li>Persistence mappings</li>
  <li>External integrations</li>
</ul>

<hr>

<h2>Project Structure</h2>

<pre>
Domain
├── user
├── product
├── category
└── shared

Application
├── user
├── product
└── services

Infrastructure
├── persistence
├── security
├── kafka
├── messaging
├── outboxworker
├── metrics
└── config

Web
├── controllers
├── filters
├── exception
└── requests
</pre>

<hr>

<h2>Persistence Architecture</h2>

<p>
  The domain model is fully isolated from persistence concerns.
</p>

<h4>Principles</h4>

<ul>
  <li>Domain layer has zero JPA dependency</li>
  <li>Persistence models remain infrastructure-specific</li>
  <li>Explicit mappers isolate transformations</li>
  <li>Repositories abstract storage implementation</li>
</ul>

<hr>

<h2>CQRS Approach</h2>

<p>
  The system applies lightweight CQRS separation.
</p>

<h3>Command Side</h3>

<ul>
  <li>Product creation</li>
  <li>User registration</li>
  <li>Authentication</li>
  <li>Domain event generation</li>
  <li>Transactional operations</li>
</ul>

<h3>Query Side</h3>

<ul>
  <li>Optimized read repositories</li>
  <li>Read-specific projections</li>
  <li>Independent query models</li>
</ul>

<hr>

<h2>Transactional Outbox Pattern</h2>

<p>
  The system implements the Transactional Outbox Pattern to guarantee consistency between database state and asynchronous messaging.
</p>

<h3>Event Flow</h3>

<pre>
Create Product
↓
Persist Product
↓
Generate Domain Event
↓
Persist Outbox Event
↓
Commit Transaction
↓
Background Worker
↓
RabbitMQ + Kafka Publication
↓
Mark Event as SENT
</pre>

<hr>

<h3>Why Outbox?</h3>

<p>
  Prevents the classic dual-write problem:
</p>

<pre>
Database updated
BUT
Message broker publication fails
</pre>

<p>
  The outbox guarantees eventual consistency between persistence and messaging systems.
</p>

<hr>

<h3>Outbox Event States</h3>

<ul>
  <li>PENDING</li>
  <li>PROCESSING</li>
  <li>SENT</li>
  <li>FAILED</li>
</ul>

<hr>

<h3>Outbox Worker</h3>

<p>
  Background scheduled worker responsible for reliable event delivery.
</p>

<pre>
@Scheduled(fixedDelay = 5000)
</pre>

<h4>Responsibilities</h4>

<ul>
  <li>Read pending events</li>
  <li>Atomic event locking</li>
  <li>Retry handling</li>
  <li>RabbitMQ publication</li>
  <li>Kafka publication</li>
  <li>Dead-letter persistence</li>
  <li>Metrics tracking</li>
  <li>Structured logging</li>
</ul>

<hr>

<h2>RabbitMQ Integration</h2>

<p>
  RabbitMQ is used for asynchronous business processing.
</p>

<h3>Features</h3>

<ul>
  <li>Exchange-based routing</li>
  <li>Queue-based communication</li>
  <li>Consumer processing</li>
  <li>Event publication</li>
</ul>

<h3>Example</h3>

<pre>
rabbitTemplate.convertAndSend(
    exchange,
    routingKey,
    payload
);
</pre>

<hr>

<h2>Kafka Integration</h2>

<p>
  Kafka is used for event streaming and analytics-oriented pipelines.
</p>

<h3>Features</h3>

<ul>
  <li>Producer/Consumer architecture</li>
  <li>Event streaming</li>
  <li>Consumer groups</li>
  <li>Structured event logging</li>
  <li>Idempotent consumer behavior</li>
</ul>

<h3>Kafka Flow</h3>

<pre>
Outbox Worker
↓
Kafka Producer
↓
Kafka Topic
↓
Kafka Consumer
↓
Analytics / Processing
</pre>

<hr>

<h2>Observability</h2>

<h3>Structured Logging</h3>

<p>
  JSON structured logs implemented using Logstash encoder.
</p>

<h4>Example Fields</h4>

<ul>
  <li>correlationId</li>
  <li>eventType</li>
  <li>eventId</li>
  <li>status</li>
  <li>attempts</li>
</ul>

<hr>

<h3>Metrics</h3>

<p>
  Micrometer instrumentation included for operational visibility.
</p>

<h4>Metrics Examples</h4>

<ul>
  <li>outbox.processed</li>
  <li>outbox.failed</li>
  <li>outbox.dlq</li>
</ul>

<hr>

<h2>API Security</h2>

<p>
  JWT-based stateless authentication implemented with Spring Security.
</p>

<h3>Features</h3>

<ul>
  <li>Access Tokens</li>
  <li>Refresh Tokens</li>
  <li>Role-based authorization</li>
  <li>Stateless security model</li>
  <li>Custom JWT filters</li>
</ul>

<hr>

<h2>Rate Limiting</h2>

<p>
  Request throttling implemented to protect endpoints against excessive traffic.
</p>

<h3>Features</h3>

<ul>
  <li>Per-client rate limiting</li>
  <li>Integration tested</li>
  <li>Header-based identification</li>
</ul>

<hr>

<h2>Integration Testing</h2>

<p>
  The project includes extensive integration testing.
</p>

<h3>Testcontainers</h3>

<p>
  Real infrastructure containers executed during tests:
</p>

<ul>
  <li>PostgreSQL</li>
  <li>RabbitMQ</li>
  <li>Kafka</li>
</ul>

<h3>Covered Scenarios</h3>

<ul>
  <li>Repository integration</li>
  <li>Transactional consistency</li>
  <li>Outbox processing</li>
  <li>Kafka publishing</li>
  <li>RabbitMQ publication</li>
  <li>Rate limiting</li>
  <li>Consumer behavior</li>
</ul>

<hr>

<h2>Docker Environment</h2>

<h3>Services</h3>

<ul>
  <li>Spring Boot API</li>
  <li>PostgreSQL</li>
  <li>RabbitMQ</li>
  <li>Kafka</li>
  <li>Kafka UI</li>
</ul>

<h3>Run Locally</h3>

<pre>
docker compose up --build
</pre>

<hr>

<h2>Kubernetes Support</h2>

<p>
  The application can run inside Kubernetes using Minikube.
</p>

<h3>Kubernetes Components</h3>

<ul>
  <li>Deployments</li>
  <li>Services</li>
  <li>Internal cluster networking</li>
  <li>DNS-based service discovery</li>
</ul>

<h3>Example Internal Services</h3>

<pre>
postgres-service:5432
rabbitmq:5672
kafka:9092
</pre>

<hr>

<h2>Current Features</h2>

<ul>
  <li>JWT Authentication</li>
  <li>Refresh Tokens</li>
  <li>Products & Categories</li>
  <li>DDD-inspired architecture</li>
  <li>CQRS separation</li>
  <li>Transactional Outbox Pattern</li>
  <li>RabbitMQ integration</li>
  <li>Kafka integration</li>
  <li>Structured logging</li>
  <li>Metrics instrumentation</li>
  <li>Rate limiting</li>
  <li>Background workers</li>
  <li>Integration testing</li>
  <li>Dockerized infrastructure</li>
  <li>Kubernetes deployment</li>
</ul>

<hr>

<h2>Future Improvements</h2>

<ul>
  <li>Distributed tracing</li>
  <li>OpenTelemetry</li>
  <li>Grafana dashboards</li>
  <li>Prometheus monitoring</li>
  <li>Dead-letter topics</li>
  <li>Horizontal autoscaling</li>
  <li>Helm charts</li>
  <li>CI/CD deployment pipelines</li>
  <li>API Gateway integration</li>
  <li>Redis caching</li>
</ul>

<hr>

<h2>Learning Goals Behind the Project</h2>

<p>
  This project was built as a backend engineering platform to practice:
</p>

<ul>
  <li>distributed systems fundamentals</li>
  <li>event-driven architecture</li>
  <li>transactional consistency</li>
  <li>asynchronous messaging</li>
  <li>infrastructure orchestration</li>
  <li>observability</li>
  <li>production-oriented backend design</li>
</ul>