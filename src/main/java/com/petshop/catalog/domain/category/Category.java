package com.petshop.catalog.domain.category;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private String imageName;
    private Boolean isCreator;
    private UUID parentId;

    private Category(UUID id, String name, String imageName, Boolean isCreator, UUID parentId) {
        this.id = id;
        updateName(name);
        updateImageName(imageName);
        updateIsCreator(isCreator);
        updateParentId(parentId);
    }

    public static Category create(String name, String imageName, UUID parentId) {
        final UUID newId = UUID.randomUUID();
        final Boolean isCreator = false;
        return new Category(newId, name, imageName, isCreator, parentId);
    }

    public static Category rehydrate(UUID id, String name, String imageName, Boolean isCreator, UUID parentId) {
        return new Category(id, name, imageName, isCreator, parentId);
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
    private void updateParentId(UUID parentId) {
        this.parentId = parentId;
    }

    public UUID getParentId() {
        return parentId;
    }
}
