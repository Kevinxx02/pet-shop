package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "product_multimedia")
public class MultimediaJpaEntity {

    @Id
    private UUID id;

    private String fileName;
    private UUID ownerId;
    private Boolean isPrimary;

    public MultimediaJpaEntity() {

    }

    public MultimediaJpaEntity(UUID id, UUID ownerId, String fileName, Boolean isPrimary) {
        setId(id);
        setFileName(fileName);
        setOwnerId(ownerId);
        setIsPrimary(isPrimary);
    }

    public UUID getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }
}