package com.petshop.catalog.domain.category;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String imageName;

    private Category(UUID id, String name, String imageName) {
        this.id = id;
        updateName(name);
        updateImageName(imageName);
    }

    public static Category create(String name, String imageName) {
        final UUID newId = UUID.randomUUID();
        return new Category(newId, name, imageName);
    }

    public static Category rehydrate(UUID id, String name, String imageName) {
        return new Category(id, name, imageName);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateImageName(String imageName) {
        this.imageName = imageName;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getImageName() {
        return this.imageName;
    }
}
