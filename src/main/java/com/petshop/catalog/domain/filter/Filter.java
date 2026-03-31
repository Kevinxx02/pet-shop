package com.petshop.catalog.domain.filter;

import java.util.UUID;

public class Filter {
    private UUID id;
    private UUID parentId;
    private UUID objectId;

    private Filter(UUID id, UUID parentId, UUID objectId) {
        this.setId(id);
        this.setParentId(parentId);
        this.setObjectId(objectId);
    }

    public static Filter create(UUID parentId, UUID objectId) {
        final UUID id = UUID.randomUUID();

        return new Filter(id, parentId, objectId);
    }

    public static Filter rehydrate(UUID id, UUID parentId, UUID objectId) {
        return new Filter(id, parentId, objectId);
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setParentId(UUID parentId) {
        this.parentId = parentId;
    }

    private void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public UUID getObjectId() {
        return this.objectId;
    }

    public UUID getParentId() {
        return this.parentId;
    }

    public UUID getId() {
        return this.id;
    }
}
