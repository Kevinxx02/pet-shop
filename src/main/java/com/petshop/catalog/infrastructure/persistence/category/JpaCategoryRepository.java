package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final SpringDataCategoryRepository jpaRepository;

    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Category> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
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
                category.id(),
                category.name(),
                category.imageName()
        );
    }

    /* Convertir de jpa a dominio */
    private Category toDomain(CategoryJpaEntity category) {
        return new Category(category.getId(), category.getName(), category.getImageName());
    }
}
