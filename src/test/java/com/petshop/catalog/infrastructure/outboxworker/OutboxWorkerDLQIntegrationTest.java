package com.petshop.catalog.infrastructure.outboxworker;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outboxdlq.OutboxDLQRepository;
import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@Transactional
class OutboxWorkerDLQIntegrationTest
        extends AbstractIntegrationTest {

    @Autowired
    private OutboxWorker worker;

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private OutboxDLQRepository dlqRepository;

    @MockBean
    private OutboxPublisher publisher;

    @Test
    void should_move_event_to_dlq_when_max_attempts_exceeded() {

        OutboxMessage message = OutboxMessage.create(
                "ProductCreated",
                "{\"name\":\"producto\"}"
        );

        OutboxEventJpaEntity entity = outboxRepository.save(message);

        entity.setAttempts(10);

        doThrow(new RuntimeException("RabbitMQ down"))
                .when(publisher)
                .publish(entity.getEventType(), entity.getPayload());

        worker.processSingleEvent(entity);

        Optional<OutboxEventJpaEntity> outboxEvent =
                outboxRepository.findById(entity.getId());

        assertTrue(outboxEvent.isEmpty());

        Optional<OutboxDLQJpaEntity> dlqEvent =
                dlqRepository.findByOriginalEventId(entity.getId());

        assertTrue(dlqEvent.isPresent());

        assertEquals(
                "ProductCreated",
                dlqEvent.get().getEventType()
        );

        assertEquals(
                "RabbitMQ down",
                dlqEvent.get().getErrorMessage()
        );
    }
}