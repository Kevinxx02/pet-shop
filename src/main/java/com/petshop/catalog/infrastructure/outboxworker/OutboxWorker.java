package com.petshop.catalog.infrastructure.outboxworker;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
public class OutboxWorker {

    private final OutboxRepository repository;
    private final OutboxPublisher publisher;

    public OutboxWorker(OutboxRepository repository,
                        OutboxPublisher publisher) {
        this.repository = repository;
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
            event.markAsFailed(e.getMessage());
        }
    }
}
