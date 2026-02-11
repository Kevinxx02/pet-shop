package com.petshop.catalog.domain.product.events;

import com.petshop.catalog.domain.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class ProductUpdated implements DomainEvent {
    private final UUID productId;
    private final Instant occurredOn;

    public ProductUpdated(UUID productId) {
        this.productId = productId;
        this.occurredOn = Instant.now();
        System.out.println("Domain/products/events/updated: " + this.getClass().getSimpleName());
    }

    public UUID getProductId() {
        return productId;
    }

    @Override
    public Instant occurredOn() {
        return occurredOn;
    }
}