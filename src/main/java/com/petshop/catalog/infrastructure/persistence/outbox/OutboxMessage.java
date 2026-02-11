package com.petshop.catalog.infrastructure.persistence.outbox;

import java.time.Instant;
import java.util.UUID;

public class OutboxMessage {

    private final UUID id;
    private final String eventType;
    private final String payload;
    private OutboxStatus status;
    private Instant occurredAt;
    private int attempts;
    private Instant nextAttemptAt;
    private String lastError;

    private OutboxMessage(UUID id,
                          String eventType,
                          String payload,
                          OutboxStatus status,
                          Instant occurredAt,
                          int attempts,
                          Instant nextAttemptAt,
                          String lastError) {
        this.id = id;
        this.eventType = eventType;
        this.payload = payload;
        this.status = status;
        this.occurredAt = occurredAt;
        this.attempts = attempts;
        this.nextAttemptAt = nextAttemptAt;
        this.lastError = lastError;
    }

    // Factory method
    public static OutboxMessage create(String eventType, String payload) {
        return new OutboxMessage(
                UUID.randomUUID(),   // id automático
                eventType,
                payload,
                OutboxStatus.PENDING,
                Instant.now(),
                0,
                Instant.now(), // procesable inmediatamente
                null
        );
    }

    public UUID getId() {
        return this.id;
    }
    public String getEventType() {
        return this.eventType;
    }

    public String getPayload() {
        return this.payload;
    }

    public OutboxStatus getStatus() {
        return this.status;
    }

    public Instant getOccurredAt() {
        return this.occurredAt;
    }
    public int getAttempts() {
        return this.attempts;
    }
    public Instant getNextAttemptAt() {
        return this.nextAttemptAt;
    }
    public String getLastError(){
        return this.lastError;
    }
}
