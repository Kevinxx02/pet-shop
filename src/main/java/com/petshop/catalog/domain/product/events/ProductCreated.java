package com.petshop.catalog.domain.product.events;

import com.petshop.catalog.domain.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class ProductCreated implements DomainEvent {
    private final UUID productId;
    private final Instant occurredOn;

    public ProductCreated(UUID productId) {
        this.productId = productId;
        this.occurredOn = Instant.now();
        System.out.println("Domain/products/events/created: " + this.getClass().getSimpleName());
    }

    public UUID getProductId() {
        return productId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}