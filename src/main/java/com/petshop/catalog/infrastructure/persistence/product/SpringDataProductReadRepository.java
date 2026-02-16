package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.list.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductReadRepository
        extends JpaRepository<ProductJpaEntity, UUID> {

    @Query("""
        select new com.petshop.catalog.application.product.list.ProductView(
            p.id, p.name, p.description, p.price, p.image, p.isVisible
        )
        from ProductJpaEntity p
    """)
    List<ProductView> findAllProjected();
    @Query("""
        select new com.petshop.catalog.application.product.list.ProductView(
            p.id, p.name, p.description, p.price, p.image, p.isVisible
        )
        from ProductJpaEntity p
        where p.isVisible
    """)
    List<ProductView> findAllVisible();
}
