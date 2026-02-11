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
        return jpaRepository.findById(id);
    }

    public void save(OutboxMessage outboxMessage) {
        jpaRepository.save(toEntity(outboxMessage));
    }

    public List<OutboxEventJpaEntity> findAll() {
        return new ArrayList<>(jpaRepository.findAll());
    }

    private OutboxEventJpaEntity toEntity(OutboxMessage outboxMessage) {
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

        return jpaRepository.findByStatusInAndNextAttemptAtLessThanEqualAndAttemptsLessThan(
                List.of(OutboxStatus.PENDING, OutboxStatus.FAILED),
                now,
                MAX_ATTEMPTS
        );
    }

    public int markAsProcessing(UUID id) {
        return jpaRepository.markAsProcessingWithAtomicLocking(id);
    }
}