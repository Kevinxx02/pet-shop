package com.petshop.catalog.infrastructure.persistence.outbox;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "outbox_events")
public class OutboxEventJpaEntity {

    @Id
    private UUID id;

    private String eventType;

    @Lob
    private String payload;

    @Enumerated(EnumType.STRING)
    private OutboxStatus status;

    private Instant occurredAt;
    protected OutboxEventJpaEntity() {}

    // NUEVOS
    private int attempts;

    private Instant nextAttemptAt;

    @Lob
    private String lastError;

    public OutboxEventJpaEntity(UUID id,
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

    public String getPayload() {
        return this.payload;
    }

    public String getEventType() {
        return this.eventType;
    }

    public void markAsFailed(String reason) {
        this.status = OutboxStatus.FAILED;
        this.nextAttemptAt = Instant.now().plusSeconds((this.attempts * 20L));
        this.lastError = reason;
    }

    public void markAsSent() {
        this.status = OutboxStatus.SENT;
    }

    public UUID getId() {
        return this.id;
    }
}
