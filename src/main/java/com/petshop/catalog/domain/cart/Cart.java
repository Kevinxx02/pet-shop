package com.petshop.catalog.domain.cart;

import java.util.*;

public class Cart {
    private UUID id;
    private UUID statusId;
    private Date createdAt;

    private Cart(UUID id, UUID statusId, Date createdAt) {
        this.id = id;
        this.statusId = statusId;
        this.createdAt = createdAt;
    }

    public static Cart create(UUID statusId) {
        return new Cart(
                UUID.randomUUID(),
                statusId,
                new Date()
        );
    }

    public static Cart rehydrate(
            UUID id,
            UUID statusId,
            Date createdAt
    ) {
        return new Cart(id, statusId, createdAt);
    }

    public UUID getId() {
        return this.id;
    }

    public UUID getStatusId() {
        return this.statusId;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void updateStatus(UUID statusId) {
        if (this.statusId == statusId) {
            return;
        }

        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}