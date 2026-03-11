package com.petshop.catalog.domain.categorygroup;

import java.util.UUID;

public class CategoryGroup {
    private UUID id;
    private String name;
    private UUID categoryId;

    private CategoryGroup(UUID id, String name, UUID categoryId) {
        this.id = id;
        setName(name);
        setCategoryId(categoryId);
    }

    public static CategoryGroup create(String name, UUID categoryId) {
        final UUID id = UUID.randomUUID();
        return new CategoryGroup(id, name, categoryId);
    }

    public static CategoryGroup rehydrate(UUID id, String name, UUID categoryId) {
        return new CategoryGroup(id, name, categoryId);
    }

    public UUID getId() {
        return id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }
}