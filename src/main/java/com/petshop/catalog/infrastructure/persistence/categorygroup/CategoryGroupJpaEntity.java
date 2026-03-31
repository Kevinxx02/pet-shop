package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.group.GroupJpaEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "category_group")
public class CategoryGroupJpaEntity {
    @Id
    private UUID id;

    @Column(name = "group_id", nullable = false)
    private UUID groupId;

    @Column(name = "category_id", nullable = false)
    private UUID categoryId;

    @Column(nullable = false)
    private boolean isVisible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private GroupJpaEntity group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryJpaEntity category;

    public CategoryGroupJpaEntity() {

    }

    public CategoryGroupJpaEntity(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isVisible
    ) {
        setId(id);
        setGroupId(groupId);
        setCategoryId(categoryId);
        setVisible(isVisible);
    }

    private void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public boolean getVisible() {
        return this.isVisible;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public UUID getGroupId() {
        return this.groupId;
    }
}