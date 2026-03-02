package com.petshop.catalog.domain.category;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String imageName;
    private Boolean isCreator;

    private Category(UUID id, String name, String imageName, Boolean isCreator) {
        this.id = id;
        updateName(name);
        updateImageName(imageName);
        updateIsCreator(isCreator);
    }

    public static Category create(String name, String imageName) {
        final UUID newId = UUID.randomUUID();
        final Boolean isCreator = false;
        return new Category(newId, name, imageName, isCreator);
    }

    public static Category rehydrate(UUID id, String name, String imageName, Boolean isCreator) {
        return new Category(id, name, imageName, isCreator);
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


    private void updateIsCreator(Boolean isCreator) {
        this.isCreator = isCreator;
    }
    public Boolean getIsCreator() {
        return this.isCreator;
    }
}
