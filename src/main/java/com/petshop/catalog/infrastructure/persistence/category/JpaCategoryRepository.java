package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final SpringDataCategoryRepository jpaRepository;
    private final CategoryMapper categoryMapper;

    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository, CategoryMapper categoryMapper) {
        this.jpaRepository = jpaRepository;
        this.categoryMapper = categoryMapper;
    }

    public Category findById(UUID id) {
        final CategoryJpaEntity category = jpaRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return this.categoryMapper.toDomain(category);
    }

    public void save(Category user) {
        jpaRepository.save(this.categoryMapper.toEntity(user));
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}
