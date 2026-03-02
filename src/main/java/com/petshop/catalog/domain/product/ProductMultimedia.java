package com.petshop.catalog.domain.product;

import java.util.UUID;

public class ProductMultimedia {

    private UUID id;
    private String fileName;

    private ProductMultimedia(UUID id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public static ProductMultimedia create(String fileName) {
        return new ProductMultimedia(UUID.randomUUID(), fileName);
    }

    public UUID getId() { return id; }
    public String getFileName() { return fileName; }
}