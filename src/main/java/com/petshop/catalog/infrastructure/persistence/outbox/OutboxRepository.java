package com.petshop.catalog.infrastructure.persistence.outbox;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OutboxRepository {
    private final SpringDataOutboxRepository jpaRepository;

    public OutboxRepository(SpringDataOutboxRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public Optional<OutboxEventJpaEntity> findById(UUID id) {
        return this.jpaRepository.findById(id);
    }

    public OutboxEventJpaEntity save(OutboxMessage outboxMessage) {
        return this.jpaRepository.save(toEntity(outboxMessage));
    }

    public List<OutboxEventJpaEntity> findAll() {
        return new ArrayList<>(this.jpaRepository.findAll());
    }

    public OutboxEventJpaEntity toEntity(OutboxMessage outboxMessage) {
        return new OutboxEventJpaEntity(
            outboxMessage.getId(),
            outboxMessage.getEventType(),
            outboxMessage.getPayload(),
            outboxMessage.getStatus(),
            outboxMessage.getOccurredAt(),
            outboxMessage.getAttempts(),
            outboxMessage.getNextAttemptAt(),
            outboxMessage.getLastError()
        );
    }

    public List<OutboxEventJpaEntity> findProcessable(Instant now) {
        int MAX_ATTEMPTS = 10;

        return this.jpaRepository.findByStatusInAndNextAttemptAtLessThanEqualAndAttemptsLessThan(
                List.of(OutboxStatus.PENDING, OutboxStatus.FAILED),
                now,
                MAX_ATTEMPTS
        );
    }

    public int markAsProcessing(UUID id) {
        return this.jpaRepository.markAsProcessingWithAtomicLocking(id);
    }

    public void deleteAll() {
        this.jpaRepository.deleteAll();
    }

    public void deleteById(UUID id) {
        this.jpaRepository.deleteById(id);
    }

    public void markAsSent(UUID id) {
        this.jpaRepository.markAsSent(id);
    }
}