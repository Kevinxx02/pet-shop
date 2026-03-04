package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryRepository implements CategoryRepository {
    private final SpringDataCategoryRepository jpaRepository;

    @PersistenceContext
    EntityManager entityManager;

    public JpaCategoryRepository(SpringDataCategoryRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<Category> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public List<Category> findVisible() {
        final Boolean isVisible = true;

        return jpaRepository.findByIsVisible(isVisible).stream().map(this::toDomain).toList();
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
        CategoryJpaEntity parent = null;

        if (category.getParentId() != null) {
            parent = entityManager.getReference(
                    CategoryJpaEntity.class,
                    category.getParentId());
        }

        return new CategoryJpaEntity(
                category.getId(),
                category.getName(),
                parent,
                category.getIsVisible()
        );
    }

    /* Convertir de jpa a dominio */
    private Category toDomain(CategoryJpaEntity category) {
        final CategoryJpaEntity parent = category.getParent();
        final UUID parentId = (parent != null) ? parent.getId() : null;

        return Category.rehydrate(category.getId(), category.getName(), parentId, category.getIsVisible());
    }

    @Override
    public Boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}
