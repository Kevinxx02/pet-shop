package com.petshop.catalog.infrastructure.persistence.product;


import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private Boolean isCreator;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProductMultimediaJpaEntity> multimedia = new HashSet<>();

    public ProductJpaEntity() {} // constructor por JPA

    public ProductJpaEntity(UUID id, String name, String description, BigDecimal price, String image, Boolean isVisible, Boolean isCreator) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.isVisible = isVisible;
        this.isCreator = isCreator;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Boolean getIsCreator() { return isCreator; }

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
    public void setIsCreator(Boolean isCreator) {
        this.isCreator = isCreator;
    }
    public void updateFrom(Product product) {
        Set<ProductMultimediaJpaEntity> imgSet = product.getMultimedia()
                .stream()
                .map(productMultimedia -> {
                    ProductMultimediaJpaEntity mm = new ProductMultimediaJpaEntity();
                    mm.setId(productMultimedia.getId());
                    mm.setFileName(productMultimedia.getFileName());
                    mm.setProduct(this);
                    return mm;
                })
                .collect(Collectors.toSet());

        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice().value();
        this.image = product.getImage();
        this.isVisible = product.getVisible();
        this.getMultimedia().clear();
        this.getMultimedia().addAll(imgSet);
    }

    public void setMultimedia(Set<ProductMultimediaJpaEntity> multimedia) {
        this.multimedia = multimedia;
    }

    public Set<ProductMultimediaJpaEntity> getMultimedia() {
        return multimedia;
    }
}