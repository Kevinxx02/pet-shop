package com.petshop.catalog.infrastructure.persistence.outbox;

public enum OutboxStatus {
    FAILED,
    PENDING,
    PROCESSING,
    SENT;

    public static OutboxStatus from(String value) {
        try {
            return OutboxStatus.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Invalid product status: " + value
            );
        }
    }
}