package com.petshop.catalog.infrastructure.persistence.categorygroup;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "category_group")
public class CategoryGroupJpaEntity {

    @Id
    private UUID id;
    private String name;

    /* Definir la relacion con categoria */
    private UUID categoryId;

    public CategoryGroupJpaEntity() {

    }

    public CategoryGroupJpaEntity(UUID id, String name, UUID categoryId) {
        setId(id);
        setName(name);
        setCategoryId(categoryId);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}