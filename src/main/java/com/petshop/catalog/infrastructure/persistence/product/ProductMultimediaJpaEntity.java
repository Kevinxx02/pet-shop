package com.petshop.catalog.infrastructure.persistence.product;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_multimedia")
public class ProductMultimediaJpaEntity {

    @Id
    private UUID id;

    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductJpaEntity product;
    public ProductMultimediaJpaEntity() {

    }

    public ProductMultimediaJpaEntity(UUID id, String fileName, ProductJpaEntity product) {
        setId(id);
        setFileName(fileName);
        setProduct(product);
    }

    public UUID getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setProduct(ProductJpaEntity product) {
        this.product = product;
    }

    public ProductJpaEntity getProduct() {
        return product;
    }
}