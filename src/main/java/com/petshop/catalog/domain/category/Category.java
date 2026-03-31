package com.petshop.catalog.domain.category;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private UUID parentId;
    private Boolean isVisible;

    private Category(UUID id, String name, UUID parentId, Boolean isVisible) {
        this.id = id;
        updateName(name);
        updateParentId(parentId);
        updateIsVisible(isVisible);
    }

    private void updateIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    public Boolean getIsVisible() {
        return this.isVisible;
    }

    public static Category create(String name, UUID parentId) {
        final UUID newId = UUID.randomUUID();
        final boolean isVisible = true;

        return new Category(newId, name, parentId, isVisible);
    }

    public static Category rehydrate(UUID id, String name, UUID parentId, Boolean isVisible) {
        return new Category(id, name, parentId, isVisible);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    private void updateParentId(UUID parentId) {
        if (this.id.equals(parentId)) {
            throw new IllegalArgumentException("Category cannot be its own parent");
        }
        this.parentId = parentId;
    }

    public UUID getParentId() {
        return parentId;
    }
    public void updateCategory(String name, UUID parentId, Boolean isVisible) {
        this.updateName(name);
        this.updateParentId(parentId);
        this.updateIsVisible(isVisible);
    }
}
