package com.petshop.catalog.infrastructure.persistence.cart;

import com.petshop.catalog.infrastructure.persistence.status.StatusJpaEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "cart")
public class CartJpaEntity {

    @Id
    private UUID id;

    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusJpaEntity status;

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