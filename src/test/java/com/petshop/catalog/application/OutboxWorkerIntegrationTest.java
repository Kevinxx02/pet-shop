package com.petshop.catalog.application;

import com.petshop.catalog.infrastructure.outboxworker.OutboxWorker;
import com.petshop.catalog.infrastructure.persistence.AbstractIntegrationTest;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxStatus;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OutboxWorkerIntegrationTest
        extends AbstractIntegrationTest {

    @Autowired
    OutboxRepository outboxRepository;

    @Autowired
    OutboxWorker worker;

    @MockBean
    RabbitTemplate rabbitTemplate;

    @Test
    void should_publish_pending_outbox_messages() {
        outboxRepository.deleteAll();

        OutboxMessage message = OutboxMessage.create(
                "ProductCreated",
                "{\"name\":\"producto\"}"
        );

        outboxRepository.save(message);

        worker.processPendingEvents();

        OutboxEventJpaEntity updated =
                outboxRepository.findById(message.getId()).orElseThrow();

        assertEquals(OutboxStatus.SENT, updated.getStatus());

        verify(rabbitTemplate)
                .convertAndSend(anyString(), anyString(), anyString());
    }
}