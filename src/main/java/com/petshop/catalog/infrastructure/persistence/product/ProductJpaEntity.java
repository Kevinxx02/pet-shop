package com.petshop.catalog.infrastructure.persistence.product;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    private UUID id;

    private String name;
    private String description;
    private BigDecimal price;

    private Boolean isVisible;

    public ProductJpaEntity() {} // constructor por JPA

    public ProductJpaEntity(UUID id, String name, String description, BigDecimal price, boolean isVisible) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setVisibility(isVisible);
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getIsVisible() {
            return isVisible;
    }
    public void setVisibility(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}