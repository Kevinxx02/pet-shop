package com.petshop.catalog.infrastructure.persistence.category;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private String imageName;

    @Version
    private Long version;

    public CategoryJpaEntity() {} // constructor por JPA

    public CategoryJpaEntity(UUID id, String name, String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
    }
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return this.imageName;
    }
    public void setImageName(String newName) {
        this.imageName = newName;
    }
}
