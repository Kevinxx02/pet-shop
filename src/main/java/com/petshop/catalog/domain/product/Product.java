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
    private String image;
    private Boolean isVisible;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Product(UUID id, String name, String description, BigDecimal price, Boolean isVisible) {
        this.id = id;
        this.changeName(name);
        this.changeDescription(description);
        this.changePrice(price);
        this.isVisible = isVisible;
    }

    private void changeImage(String image) {
        this.image = image;
    }

    public static Product create(
            String name,
            String description,
            BigDecimal price,
            Boolean isVisible
    ) {
        UUID newId = UUID.randomUUID();
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
            String image,
            Boolean isVisible
    ) {
        return new Product(id, name, description, price, isVisible);
    }

    public static Product update(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Boolean isVisible
    ) {

        Product product =  new Product(id, name, description, price, isVisible);
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

    private void changePrice(BigDecimal price) {
        this.price = ProductPrice.from(price);
    }

    private void changeDescription(String description) {
        this.description = description;
    }

    private void changeName(String name) {
        this.name = name;
    }
    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getVisible() {
        return isVisible;
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
