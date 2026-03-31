package com.petshop.catalog.domain.categorygroup;

import java.util.UUID;

public class CategoryGroup {
    private final UUID id;
    private UUID groupId;
    private UUID categoryId;
    private boolean isVisible;

    private CategoryGroup(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isVisible
    ) {
        this.id = id;
        this.setGroupId(groupId);
        this.setCategoryId(categoryId);
        this.setVisible(isVisible);
    }

    public void update(UUID groupId, UUID categoryId, boolean isVisible) {
        this.setGroupId(groupId);
        this.setCategoryId(categoryId);
        this.setVisible(isVisible);
    }

    private void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static CategoryGroup create(
            UUID groupId,
            UUID categoryId
    ) {
        final UUID id = UUID.randomUUID();
        final boolean isVisible = true;

        return new CategoryGroup(id, groupId, categoryId, isVisible);
    }

    public static CategoryGroup rehydrate(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isVisible
    ) {
        return new CategoryGroup(id, groupId, categoryId, isVisible);
    }

    public UUID getId() {
        return id;
    }

    private void setGroupId(UUID groupId) {
        if (groupId == null) {
            throw new IllegalArgumentException("categoryId cannot be null");
        }
        this.groupId = groupId;
    }

    private void setCategoryId(UUID categoryId) {
        if (categoryId == null) {
            throw new IllegalArgumentException("categoryId cannot be null");
        }

        this.categoryId = categoryId;
    }

    public UUID getGroupId() {
        return this.groupId;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public boolean getVisible() {
        return this.isVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryGroup that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}