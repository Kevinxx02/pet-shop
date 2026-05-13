package com.petshop.catalog.infrastructure.persistence.outboxdlq;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "dead_letter_queque")
public class OutboxDLQJpaEntity {
    @Id
    private UUID id;

    private UUID originalEventId;

    private String eventType;

    @Column(columnDefinition = "TEXT")
    private String payload;

    private int attempts;

    private String errorMessage;

    private Instant failedAt;

    OutboxDLQJpaEntity() {}

    public OutboxDLQJpaEntity(
            UUID id,
            UUID originalEventId,
            String eventType,
            String payload,
            int attempts,
            String errorMessage,
            Instant failedAt
    ) {
        this.id = id;
        this.originalEventId = originalEventId;
        this.eventType = eventType;
        this.payload = payload;
        this.attempts = attempts;
        this.errorMessage = errorMessage;
        this.failedAt = failedAt;
    }

    public String getEventType() {
        return this.eventType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
