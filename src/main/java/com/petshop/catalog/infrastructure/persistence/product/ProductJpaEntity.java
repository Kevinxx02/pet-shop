package com.petshop.catalog.infrastructure.persistence.product;


import com.petshop.catalog.domain.product.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private String description;
    private BigDecimal price;
    private String image;

    @Version
    private Long version;
    private Boolean isVisible;

    protected ProductJpaEntity() {} // constructor por JPA

    public ProductJpaEntity(UUID id, String name, String description, BigDecimal price, String image, Boolean isVisible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.isVisible = isVisible;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getImage() {
        return this.image;
    }
    public Boolean getIsVisible() {
            return isVisible;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setVisibility(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public void updateFrom(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice().value();
        this.image = product.getImage();
        this.isVisible = product.getVisible();
    }
}