package com.petshop.catalog.infrastructure.outboxworker;

import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OutboxWorkerMetricsTest extends AbstractIntegrationTest {

    private final OutboxRepository repository;
    private final OutboxWorker worker;
    private final MeterRegistry meterRegistry;

    @MockBean
    private OutboxPublisher publisher;

    @Autowired
    OutboxWorkerMetricsTest(
            OutboxRepository repository,
            OutboxWorker worker,
            MeterRegistry meterRegistry
    ) {
        this.repository = repository;
        this.worker = worker;
        this.meterRegistry = meterRegistry;
    }

    @Test
    void should_increment_processed_counter_when_event_is_sent() {

        OutboxEventJpaEntity event = repository.save(
                OutboxMessage.create("Product created", "{\"name\":\"p\"}")
        );

        worker.processSingleEvent(event);

        Counter counter = meterRegistry.find("outbox.processed").counter();

        assertNotNull(counter);
        assertEquals(1.0, counter.count());
    }

    @Test
    void should_increment_failed_counter_when_publish_fails() {

        OutboxEventJpaEntity event = repository.save(
                OutboxMessage.create("Product created", "{\"name\":\"p\"}")
        );

        doThrow(new RuntimeException("fail"))
                .when(publisher)
                .publish(any(), any());

        worker.processSingleEvent(event);

        Counter counter = meterRegistry.find("outbox.failed").counter();

        assertNotNull(counter);
        assertEquals(1.0, counter.count());
    }

    @Test
    void should_increment_dlq_counter_when_event_sent_to_dlq() {

        OutboxEventJpaEntity event = repository.save(
                OutboxMessage.create("Product created", "{\"name\":\"p\"}")
        );

        event.setAttempts(10);

        doThrow(new RuntimeException("fatal error"))
                .when(publisher)
                .publish(any(), any());

        worker.processSingleEvent(event);

        Counter counter = meterRegistry.find("outbox.dlq").counter();

        assertNotNull(counter);
        assertEquals(1.0, counter.count());
    }
}