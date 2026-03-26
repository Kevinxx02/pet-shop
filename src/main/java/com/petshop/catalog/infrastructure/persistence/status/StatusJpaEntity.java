package com.petshop.catalog.infrastructure.persistence.status;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "statuses")
public class StatusJpaEntity {

    @Id
    private UUID id;

    private String name;

    protected StatusJpaEntity() {

    }

    public StatusJpaEntity(UUID id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}