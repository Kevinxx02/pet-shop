package com.petshop.catalog.infrastructure.persistence.category;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private Boolean isVisible;

    public CategoryJpaEntity() {} // constructor por JPA

    public CategoryJpaEntity(UUID id, String name, Boolean isVisible) {
        setId(id);
        setName(name);
        setIsVisible(isVisible);
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

    public Boolean getIsVisible() {
        return this.isVisible;
    }
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }
}
