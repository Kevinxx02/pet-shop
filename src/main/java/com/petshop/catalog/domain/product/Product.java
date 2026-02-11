package com.petshop.catalog.domain.product;

import com.petshop.catalog.domain.DomainEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
public class Product {

    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product create(
            String name,
            String description,
            BigDecimal price
    ) {
        UUID newId = UUID.randomUUID();
        Product product = new Product(
                UUID.randomUUID(),
                name,
                description,
                price
        );

        product.recordEvent(new ProductCreated(newId));

        return product;
    }

    public static Product rehydrate(
            UUID id,
            String name,
            String description,
            BigDecimal price
    ) {
        return new Product(id, name, description, price);
    }
    public UUID getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear(); // limpia la lista después de “sacar” los eventos
        return events;
    }
}
