package com.petshop.catalog.infrastructure.persistence.cart;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class CartJpaEntity {

    @Id
    private UUID id;
    private UUID statusId;
    private Date createdAt;

    protected CartJpaEntity() {}

    CartJpaEntity(UUID id, UUID statusId, Date createdAt) {
        setId(id);
        setStatusId(statusId);
        setCreatedAt(createdAt);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public UUID getStatusId() {
        return statusId;
    }
}