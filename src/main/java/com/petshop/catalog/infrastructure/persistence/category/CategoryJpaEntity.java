package com.petshop.catalog.infrastructure.persistence.category;
import com.petshop.catalog.domain.category.Category;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "categories")
public class CategoryJpaEntity {

    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    private String name;
    private String imageName;
    private Boolean isVisible;

    @Version
    private Long version;

    @ManyToOne
    @JoinColumn(name = "parent")
    private CategoryJpaEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<CategoryJpaEntity> children = new ArrayList<>();

    public CategoryJpaEntity() {} // constructor por JPA

    public CategoryJpaEntity(UUID id, String name, CategoryJpaEntity parent, Boolean isVisible) {
        setId(id);
        setName(name);
        setIsVisible(isVisible);
        setParent(parent);
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

    public void updateFrom(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.isVisible = category.getIsVisible();
    }

    public Boolean getIsVisible() {
        return this.isVisible;
    }
    public void setIsVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public CategoryJpaEntity getParent() {
        return this.parent;
    }

    public void setParent(CategoryJpaEntity parent) {
        this.parent = parent;
    }
}
