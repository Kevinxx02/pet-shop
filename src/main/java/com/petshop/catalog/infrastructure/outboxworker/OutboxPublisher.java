package com.petshop.catalog.infrastructure.outboxworker;

public interface OutboxPublisher {
    void publish(String eventType, String payload);
}
