package com.petshop.catalog.infrastructure.persistence.product;


import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.infrastructure.persistence.productcategory.ProductCategoryJpaEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<ProductCategoryJpaEntity> categories;

    private String name;
    private String description;
    private BigDecimal price;

    @Version
    private Long version;
    private Boolean isVisible;

    public ProductJpaEntity() {} // constructor por JPA

    public ProductJpaEntity(UUID id, String name, String description, BigDecimal price, String image, Boolean isVisible) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
    public Boolean getIsVisible() {
            return isVisible;
    }
    public void setVisibility(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    public void updateFrom(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice().value();
        this.isVisible = product.getVisible();
    }

    public Set<ProductCategoryJpaEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<ProductCategoryJpaEntity> categories) {
        this.categories = categories;
    }
}