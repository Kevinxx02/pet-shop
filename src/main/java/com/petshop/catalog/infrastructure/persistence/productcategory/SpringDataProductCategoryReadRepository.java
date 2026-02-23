package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.product.ProductView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductCategoryReadRepository extends JpaRepository<ProductCategoryJpaEntity, UUID>
{
    @Query("""
    SELECT new com.petshop.catalog.application.product.ProductView(
        p.id, p.name, p.description, p.price, p.image, p.isVisible, p.isCreator
    )
    FROM ProductJpaEntity p
    JOIN ProductCategoryJpaEntity pc ON p.id = pc.id.productId
    JOIN CategoryJpaEntity c ON pc.id.categoryId = c.id
    WHERE c.id = :categoryId
      AND p.isVisible = true
""")
    List<ProductView> listProductsFromCategory(@Param("categoryId") UUID categoryId);
}
