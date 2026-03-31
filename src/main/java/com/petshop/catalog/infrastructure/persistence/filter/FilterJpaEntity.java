package com.petshop.catalog.infrastructure.persistence.filter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "filters")
public class FilterJpaEntity {
    @Id
    private UUID id;
    private UUID parentId;
    private UUID objectId;

    protected FilterJpaEntity() {}
    public FilterJpaEntity(UUID id, UUID parentId, UUID objectId) {
        this.setId(id);
        this.setParentId(parentId);
        this.setObjectId(objectId);
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
        return objectId;
    }

    public UUID getParentId() {
        return parentId;
    }

    public UUID getId() {
        return id;
    }
}
