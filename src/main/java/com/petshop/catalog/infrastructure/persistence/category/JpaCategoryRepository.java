package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final SpringDataCategoryRepository jpaRepository;

    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Category> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Category> findVisible() {
        final Boolean isCreator = false;

        return jpaRepository.findByIsCreator(isCreator).stream().map(this::toDomain).toList();
    }

    public Optional<CategoryJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }


    public void save(Category user) {
        jpaRepository.save(toEntity(user));
    }
    public void save(CategoryJpaEntity product) {
        jpaRepository.save(product);
    }

    // Convertir de dominio a JPA
    private CategoryJpaEntity toEntity(Category category) {
        return new CategoryJpaEntity(
                category.getId(),
                category.getName(),
                category.getImageName()
        );
    }

    /* Convertir de jpa a dominio */
    private Category toDomain(CategoryJpaEntity category) {
        return Category.rehydrate(category.getId(), category.getName(), category.getImageName(), category.getIsCreator());
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}
