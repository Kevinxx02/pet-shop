package com.petshop.catalog.domain.category;

import java.util.UUID;

public class Category {
    private UUID id;
    private String name;
    private Boolean isVisible;

    private Category(UUID id, String name, Boolean isVisible) {
        this.id = id;
        updateName(name);
        updateIsVisible(isVisible);
    }

    private void updateIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
    public Boolean getIsVisible() {
        return this.isVisible;
    }

    public static Category create(String name) {
        final UUID newId = UUID.randomUUID();
        final boolean isVisible = true;

        return new Category(newId, name, isVisible);
    }

    public static Category rehydrate(UUID id, String name, Boolean isVisible) {
        return new Category(id, name, isVisible);
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


    public void updateCategory(String name, Boolean isVisible) {
        this.updateName(name);
        this.updateIsVisible(isVisible);
    }
}
