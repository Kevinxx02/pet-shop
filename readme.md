# Pet Shop Catalog

Este proyecto es un ejemplo de una **aplicación de catálogo de productos** con enfoque en **DDD, CQRS y Event-Driven Architecture**, usando **Spring Boot**, **JPA**, **Outbox Pattern** y **RabbitMQ**.

---

## Características del proyecto

- **CQRS básico**  
  Separación entre escritura (`CreateProductService`) y lectura (`ProductView`, `SpringDataProductReadRepository`).

- **Domain Events + Outbox Pattern**
    - Cada agregado (`Product`) registra eventos (`ProductCreated`).
    - Eventos guardados en tabla `outbox_events` dentro de la misma transacción que el CRUD.
    - Worker periódico procesa eventos pendientes, asegura idempotencia y retry con backoff.

- **RabbitMQ (Integración básica)**
    - Publica eventos desde el outbox hacia RabbitMQ.
    - Consumidor recibe payloads y los procesa.
    - Base para sistemas distribuidos.

- **Concurrencia y consistencia**
    - Update atómico para evitar doble publicación de eventos.
    - Locks opcionales: **pessimistic** y **optimistic** (`@Version`) para control de concurrencia.

- **Transacciones**
    - `@Transactional` para comandos.
    - `@Transactional(readOnly = true)` para consultas optimizadas.

---

## Estructura del proyecto
src/
├─ main/
│ ├─ java/com/petshop/catalog
│ │ ├─ application/ # Lógica de Application Services
│ │ │ ├─ product/create
│ │ │ ├─ product/update
│ │ │ └─ product/list
│ │ │ ├─ user/create
│ │ │ ├─ user/update
│ │ │ └─ user/list
│ │ ├─ domain/ # Agregados, Value Objects, Domain Events
│ │ │ ├─ Product
│ │ │ └─ User
│ │ ├─ infrastructure/ # Persistencia (JPA, Outbox), RabbitMQ, seeder
│ │ └─ web/ # Controladores REST
│ └─ resources/
│ ├─ application.properties
│ └─ db/migration

1. **Base de datos**: PostgreSQL.
2. **RabbitMQ**: debe estar corriendo en `localhost:5672` (configurable).
3. **Dependencias**: Spring Boot, JPA, Jackson, RabbitMQ Client.

**Ejemplo application.properties:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/petshop
spring.datasource.username=usuario
spring.datasource.password=contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672

Ejecución

Levantar base de datos y RabbitMQ.

Ejecutar la aplicación (mvn spring-boot:run).

Endpoints disponibles:

Método	Ruta	    Descripción
POST	/products	Crear producto
PUT	    /products	Actualizar producto
GET	    /products	Listar productos (CQRS read) (Acepta parametro id, para devolver ese registro. Y Acepta el parametro isAdmin, para devolver productos ocultos.)
