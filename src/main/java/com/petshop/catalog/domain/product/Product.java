package com.petshop.catalog.domain.product;

import com.petshop.catalog.domain.DomainEvent;
import com.petshop.catalog.domain.product.events.ProductCreated;

import java.math.BigDecimal;
import java.util.*;

public class Product {

    private final UUID id;
    private String name;
    private String description;
    private ProductPrice price;
    private boolean isVisible;
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Product(UUID id, String name, String description, BigDecimal price, boolean isVisible) {
        this.id = id;
        this.changeName(name);
        this.changeDescription(description);
        this.changePrice(price);
        this.changeVisibility(isVisible);
    }

    public static Product create(
            String name,
            String description,
            BigDecimal price
    ) {
        final boolean isVisible = true;
        final UUID newId = UUID.randomUUID();

        Product product = new Product(
                newId,
                name,
                description,
                price,
                isVisible
        );

        product.recordEvent(new ProductCreated(newId));

        return product;
    }

    public static Product rehydrate(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            boolean isVisible
    ) {
        return new Product(id, name, description, price, isVisible);
    }

    public void update(
            String name,
            String description,
            BigDecimal price,
            boolean isVisible
    ) {
        this.changeName(name);
        this.changeDescription(description);
        this.changePrice(price);
        this.changeVisibility(isVisible);
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

    private void changePrice(BigDecimal price) {
        /* Las validaciones de price se encuentran en el VO ProductPrice */
        this.price = ProductPrice.from(price);
    }

    private void changeDescription(String description) {
        if (Objects.equals(this.description, description)) return;

        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.description = description;
    }

    private void changeName(String name) {
        if (Objects.equals(this.name, name)) return;

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.name = name;
    }

    public boolean getVisible() {
        return isVisible;
    }

    private void changeVisibility(boolean isVisible) {
        this.isVisible = isVisible;
}

    private void recordEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> events = new ArrayList<>(domainEvents);
        domainEvents.clear();
        return events;
    }

}
