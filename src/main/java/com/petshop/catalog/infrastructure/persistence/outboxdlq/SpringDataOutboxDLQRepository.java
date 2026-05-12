package com.petshop.catalog.infrastructure.persistence.outboxdlq;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataOutboxDLQRepository extends JpaRepository<OutboxDLQJpaEntity, UUID> {
    Optional<OutboxDLQJpaEntity> findByOriginalEventId(UUID id);
}