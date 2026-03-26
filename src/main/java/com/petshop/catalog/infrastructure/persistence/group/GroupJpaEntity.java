package com.petshop.catalog.infrastructure.persistence.group;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "groups")
public class GroupJpaEntity {
    @Id
    private UUID id;
    private String name;

    protected GroupJpaEntity() {}
    public GroupJpaEntity(UUID id, String name) {
        this.setId(id);
        this.setName(name);
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
