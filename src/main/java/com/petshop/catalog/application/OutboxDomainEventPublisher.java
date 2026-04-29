package com.petshop.catalog.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.application.product.DomainEventPublisher;
import com.petshop.catalog.domain.DomainEvent;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import org.springframework.stereotype.Component;

@Component
public class OutboxDomainEventPublisher implements DomainEventPublisher {

    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;

    public OutboxDomainEventPublisher(
            OutboxRepository outboxRepository,
            ObjectMapper objectMapper
    ) {
        this.outboxRepository = outboxRepository;
        this.objectMapper = objectMapper;
    }

    public void publish(DomainEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);

            OutboxMessage msg = OutboxMessage.create(
                    event.getClass().getSimpleName(),
                    payload
            );

            outboxRepository.save(msg);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize domain event", e);
        }
    }
}