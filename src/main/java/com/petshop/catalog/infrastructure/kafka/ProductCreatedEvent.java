package com.petshop.catalog.infrastructure.kafka;

import java.util.UUID;

public record ProductCreatedEvent(UUID productId, String name ) {}
