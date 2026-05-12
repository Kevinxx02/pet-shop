package com.petshop.catalog.infrastructure.outboxworker;
import static net.logstash.logback.argument.StructuredArguments.kv;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQRepository;
import com.petshop.catalog.web.correlation.CorrelationIdHolder;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Log4j2
@Component
public class OutboxWorker {

    private final OutboxRepository repository;
    private final OutboxDLQRepository DLQRepository;
    private final CorrelationIdHolder holder;

    private final OutboxPublisher publisher;
    private static final int MAX_ATTEMPTS = 10;

    private final Counter processedEvents;
    private final Counter failedEvents;
    private final Counter dlqEvents;

    public OutboxWorker(
            OutboxRepository repository,
            OutboxDLQRepository DLQRepository,
            CorrelationIdHolder holder,
            OutboxPublisher publisher,
            MeterRegistry registry
    ) {
        this.repository = repository;
        this.DLQRepository = DLQRepository;
        this.holder = holder;
        this.publisher = publisher;

        this.processedEvents = registry.counter("outbox.processed", "status", "success");
        this.failedEvents = registry.counter("outbox.failed", "status", "retry");
        this.dlqEvents = registry.counter("outbox.dlq", "status", "dead");
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

            this.processedEvents.increment();

            log.info("outbox event processed",
                    kv("eventType", event.getEventType()),
                    kv("eventId", event.getId()),
                    kv("status", "PROCESSED"),
                    kv("attempts", event.getAttempts())
            );
        } catch (Exception e) {
            failedEvents.increment();

            if (event.getAttempts() < MAX_ATTEMPTS) {
                event.markAsFailed(e.getMessage());
            } else {
                dlqEvents.increment();

                final OutboxDLQJpaEntity deadLetter = this.DLQRepository.create(event, e);
                this.DLQRepository.save(deadLetter);

                this.repository.deleteById(event.getId());
            }
        }
    }
}