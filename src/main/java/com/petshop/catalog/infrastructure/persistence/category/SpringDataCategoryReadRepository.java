package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.application.category.CategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataCategoryReadRepository extends JpaRepository<CategoryJpaEntity, UUID> {
    @Query("""
        SELECT
            new com.petshop.catalog.application.category.CategoryView(id, name)
        FROM CategoryJpaEntity
        WHERE isVisible = true
    """)
    List<CategoryView> viewAll();
}
