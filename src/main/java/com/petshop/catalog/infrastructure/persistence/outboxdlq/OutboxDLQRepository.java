package com.petshop.catalog.infrastructure.persistence.outboxdlq;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public class OutboxDLQRepository {
    private final SpringDataOutboxDLQRepository jpaRepository;

    public OutboxDLQRepository(SpringDataOutboxDLQRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public OutboxDLQJpaEntity create(OutboxEventJpaEntity event, Exception e) {
        final UUID newId = UUID.randomUUID();

        return new OutboxDLQJpaEntity(
                newId,
                event.getId(),
                event.getEventType(),
                event.getPayload(),
                event.getAttempts(),
                e.getMessage(),
                event.getOccurredAt()
        );
    }
    public Optional<OutboxDLQJpaEntity> findByOriginalEventId(UUID id){
        return this.jpaRepository.findByOriginalEventId(id);
    }
    public void save(OutboxDLQJpaEntity deadLetter) {
        this.jpaRepository.save(deadLetter);
    }
}