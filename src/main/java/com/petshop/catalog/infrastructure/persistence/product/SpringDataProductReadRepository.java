package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductReadRepository
        extends JpaRepository<ProductJpaEntity, UUID> {

    @Query("""
        SELECT new com.petshop.catalog.application.product.ProductView(
            p.id, p.name, p.description, p.price, p.image, p.isVisible, p.isCreator
        )
        FROM ProductJpaEntity p
    """)
    List<ProductView> findAllProjected();
    @Query("""
        SELECT new com.petshop.catalog.application.product.ProductView(
            p.id, p.name, p.description, p.price, p.image, p.isVisible, p.isCreator
        )
        FROM ProductJpaEntity p
        WHERE p.isVisible
    """)
    List<ProductView> findVisibleProjected();

    @Query("""
        SELECT new com.petshop.catalog.application.product.ProductView(
            p.id, p.name, p.description, p.price, p.image, p.isVisible, p.isCreator
        )
        FROM ProductJpaEntity p
        WHERE p.isVisible AND p.id = :id
    """)
    List<ProductView> findByIdProjected(@Param("id") UUID id);
}
