package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.productcategory.ProductCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductCategoryReadRepository
        extends JpaRepository<ProductCategoryJpaEntity, UUID>
{
    @Query("""
        SELECT new com.petshop.catalog.application.productcategory.ProductCategoryView(
            id,
            productId,
            categoryId
        )
        FROM ProductCategoryJpaEntity pc
    """)
    List<ProductCategoryView> viewAll();
}
