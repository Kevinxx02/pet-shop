package com.petshop.catalog.domain.categorygroup;

import java.util.UUID;

public class CategoryGroup {
    private final UUID id;
    private UUID groupId;
    private UUID categoryId;
    private boolean isActive;

    private CategoryGroup(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isActive
    ) {
        this.id = id;
        this.setGroupId(groupId);
        this.setCategoryId(categoryId);
        this.setIsActive(isActive);
    }

    public void update(UUID groupId, UUID categoryId, boolean isActive) {
        this.setGroupId(groupId);
        this.setCategoryId(categoryId);
        this.setIsActive(isActive);
    }

    private void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static CategoryGroup create(
            UUID groupId,
            UUID categoryId
    ) {
        final UUID id = UUID.randomUUID();
        final boolean isActive = true;

        return new CategoryGroup(id, groupId, categoryId, isActive);
    }

    public static CategoryGroup rehydrate(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isActive
    ) {
        return new CategoryGroup(id, groupId, categoryId, isActive);
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

    public boolean getIsActive() {
        return this.isActive;
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