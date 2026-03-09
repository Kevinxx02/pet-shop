package com.petshop.catalog.domain.multimedia;

import java.util.UUID;

public class Multimedia {
    private UUID id;
    private String fileName;
    private Boolean isPrimary;
    private UUID ownerId;

    private Multimedia(UUID id, UUID ownerId, String fileName, Boolean isPrimary) {
        this.id = id;
        this.fileName = fileName;
        this.isPrimary = isPrimary;
        this.ownerId = ownerId;
    }

    public static Multimedia create(UUID ownerId, String fileName, Boolean isPrimary) {
        final UUID id = UUID.randomUUID();
        return new Multimedia(id, ownerId, fileName, isPrimary);
    }
    public static Multimedia rehydrate(UUID id, UUID ownerId, String fileName, Boolean isPrimary) {
        return new Multimedia(id, ownerId, fileName, isPrimary);
    }

    public UUID getId() { return id; }
    public String getFileName() { return fileName; }
    public UUID getOwnerId() {
        return ownerId;
    }
    public Boolean getIsPrimary() {
        return this.isPrimary;
    }
}