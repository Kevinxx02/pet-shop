package com.petshop.catalog.application.product.list;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductView {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;

    public ProductView(UUID id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // getters
    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
}
