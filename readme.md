# PetShop Catalog API

Backend del sistema de catálogo de PetShop.

Arquitectura basada en:

- **DDD (Domain Driven Design) ligero**
- **CQRS (separación de lectura y escritura a nivel de aplicación)**
- **JPA desacoplado del dominio mediante mappers**
- **Outbox Pattern**
- **Mensajería asíncrona con RabbitMQ**
- **Transacciones ACID en capa de aplicación**

---

# Stack tecnológico

- Java 17
- Spring Boot 3
- Spring Security + JWT
- Spring Data JPA (Hibernate)
- PostgreSQL
- RabbitMQ
- Flyway
- Maven

---

# Arquitectura general

El sistema sigue una arquitectura en capas inspirada en DDD, con separación estricta entre dominio y persistencia.


domain<br>
├── user<br>
├── product<br>
├── category<br>
└── shared<br>

application<br>
├── user (auth/login/register)<br>
├── product (use cases)<br>
└── services<br>

infrastructure<br>
├── persistence (JPA entities + repositories)<br>
├── security (JWT, filters, config)<br>
├── messaging (RabbitMQ)<br>
└── outboxworker<br>


---

# Arquitectura de persistencia (DDD + JPA)

El proyecto separa completamente el modelo de dominio del modelo de persistencia.

## Principios

- El dominio NO depende de JPA
- Las entidades JPA están en infraestructura
- La conversión se realiza mediante mappers explícitos

## Flujo de datos


Dominio → Mapper → JPA Entity → Repository → DB
DB → JPA Entity → Mapper → Dominio


## Ejemplo conceptual

- `Product` → entidad de dominio
- `ProductJpaEntity` → modelo persistente
- `ProductMapper` → traducción entre ambos

---

# CQRS (Command Query Responsibility Segregation)

El sistema aplica CQRS de forma ligera:

## Command side (escritura)

- Creación de productos
- Registro de usuarios
- Login
- Generación de eventos de dominio
- Uso de transacciones

## Query side (lectura)

- Repositorios de lectura (ReadRepository, etc.)
- Consultas optimizadas sin lógica de negocio
- Separación de modelos cuando es necesario

---

# Transacciones

La consistencia del sistema depende de transacciones en la capa de aplicación:

## Uso principal

- Creación de entidades de dominio
- Persistencia en base de datos
- Inserción en Outbox
- Registro de eventos

## Ejemplo conceptual


@Transactional
createProduct():

guardar producto
generar evento de dominio
persistir outbox event

Esto garantiza:

- atomicidad entre dominio y outbox
- consistencia eventual con RabbitMQ

---

# Módulos principales

---

## 1. Autenticación

Sistema basado en Spring Security + JWT.

### Flujo

- Login con email y password
- AuthenticationManager valida credenciales
- Se generan:
  - Access Token
  - Refresh Token

```java
Authentication auth = authenticationManager.authenticate(
    new UsernamePasswordAuthenticationToken(email, password)
);
```
## 2. Productos

Entidad central del dominio.

Flujo de creación<br>
Se crea entidad en dominio<br>
Se persiste en base de datos<br>
Se genera evento de dominio (ProductCreated)<br>
Se almacena en Outbox dentro de la misma transacción<br>

## 3. Categorías

Relación con productos<br>
Modelo de referencia de dominio<br>
Puede extenderse a múltiples productos<br>

## 4. Outbox Pattern

Garantiza consistencia entre base de datos y mensajería.

Flujo<br>
Se genera evento de dominio<br>
Se persiste en tabla outbox_events<br>
Worker procesa eventos pendientes<br>
Se publica en RabbitMQ<br>
Se marca como SENT<br><br>
Estados:
PENDING
PROCESSING
SENT
FAILED

## 5. Outbox Worker

Proceso en background:

@Scheduled(fixedDelay = 5000)<br>
Responsabilidades<br>
Leer eventos pendientes<br>
Bloquear eventos (concurrencia segura)<br>
Publicar en RabbitMQ<br>
Actualizar estado final<br>

## 6. RabbitMQ

Sistema de mensajería basado en exchanges y routing keys.

Exchange principal: petshop.exchange<br><br>
Routing keys: product.created<br><br>
Publicación: rabbitTemplate.convertAndSend(exchange, routingKey, payload);

## 7. Seguridad

JWT stateless authentication<br>
Roles en claims<br>
Refresh token para renovación<br>
Integración con Spring Security<br>

## 8. Configuración de perfiles
DEV
logging DEBUG
RabbitMQ activo
Worker activo
configuración flexible
PROD
logging reducido

## -- Estado del proyecto -- 
 Autenticación JWT <br>
 Refresh tokens <br>
 Productos y categorías <br>
 DDD con separación dominio / infraestructura
 JPA desacoplado mediante mappers <br>
 CQRS (ligero) <br>
 Outbox Pattern <br>
 RabbitMQ integration <br>
 Worker programado <br>
 Tests <br>
 Dockerización <br>