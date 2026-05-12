package com.petshop.catalog.infrastructure.outboxworker;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class OutboxWorker {

    private final OutboxRepository repository;
    private final OutboxDLQRepository DLQRepository;

    private final OutboxPublisher publisher;
    private static final int MAX_ATTEMPTS = 10;

    public OutboxWorker(
            OutboxRepository repository,
            OutboxDLQRepository DLQRepository,
            OutboxPublisher publisher
    ) {
        this.repository = repository;
        this.DLQRepository = DLQRepository;
        this.publisher = publisher;
    }

    @Scheduled(fixedDelay = 5000)
    public void processPendingEvents() {
        List<OutboxEventJpaEntity> events =
                repository.findProcessable(Instant.now());

        for (OutboxEventJpaEntity event : events) {
            processSingleEvent(event);
        }
    }

    @Transactional
    public void processSingleEvent(OutboxEventJpaEntity event) {
        try {
            int updated = repository.markAsProcessing(event.getId());

            if (updated == 0) return;

            publisher.publish(event.getEventType(), event.getPayload());

            event.markAsSent();

        } catch (Exception e) {
            if (event.getAttempts() < MAX_ATTEMPTS) {
                event.markAsFailed(e.getMessage());
            } else {
                final OutboxDLQJpaEntity deadLetter = this.DLQRepository.create(event, e);
                this.DLQRepository.save(deadLetter);

                this.repository.deleteById(event.getId());
            }
        }
    }
}
