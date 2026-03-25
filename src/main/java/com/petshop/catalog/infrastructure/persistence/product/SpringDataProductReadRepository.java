package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductReadRepository
        extends JpaRepository<ProductJpaEntity, UUID> {
    @Query("""
        SELECT new com.petshop.catalog.application.product.ProductView(
            p.id, p.name, p.description, p.price
        )
        FROM ProductJpaEntity p
        LEFT JOIN ProductCategoryJpaEntity pc
            ON p.id = pc.product.id
        WHERE p.isVisible
    """)
    List<ProductView> findAllView();
}
