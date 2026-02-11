package com.petshop.catalog.domain.product;

import com.petshop.catalog.domain.DomainEvent;
import com.petshop.catalog.domain.product.events.ProductCreated;
import com.petshop.catalog.domain.product.events.ProductUpdated;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {

    private final UUID id;
    private String name;
    private String description;
    private ProductPrice price;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Product(UUID id, String name, String description, BigDecimal price) {
        this.id = id;
        this.changeName(name);
        this.changeDescription(description);
        this.changePrice(price);
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

    public static Product update(
            UUID id,
            String name,
            String description,
            BigDecimal price
    ) {

        Product product =  new Product(id, name, description, price);
        product.recordEvent(new ProductUpdated(product.id));

        return product;
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
    public ProductPrice getPrice() {
        return this.price;
    }

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

    private void changePrice(BigDecimal price) {
        this.price = ProductPrice.from(price);
    }

    private void changeDescription(String description) {
        this.description = description;
    }

    private void changeName(String name) {
        this.name = name;
    }
}
