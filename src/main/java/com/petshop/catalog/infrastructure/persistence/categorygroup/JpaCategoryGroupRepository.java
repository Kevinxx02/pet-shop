package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroup;
import com.petshop.catalog.domain.categorygroup.CategoryGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaCategoryGroupRepository implements CategoryGroupRepository {

    private final SpringDataCategoryGroupRepository jpaRepository;

    public JpaCategoryGroupRepository(SpringDataCategoryGroupRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CategoryGroupJpaEntity categoryGroup) {
        jpaRepository.save(categoryGroup);
    }

    @Override
    public CategoryGroupJpaEntity toEntity(CategoryGroup categoryGroup) {
        CategoryGroupJpaEntity entity = new CategoryGroupJpaEntity();
        entity.setId(categoryGroup.getId());
        entity.setName(categoryGroup.getName());
        entity.setCategoryId(categoryGroup.getCategoryId());

        return entity;
    }

    @Override
    public CategoryGroup toDomain(CategoryGroupJpaEntity entity) {
        return CategoryGroup.rehydrate(entity.getId(), entity.getName(), entity.getCategoryId());
    }

    @Override
    public List<CategoryGroup> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
