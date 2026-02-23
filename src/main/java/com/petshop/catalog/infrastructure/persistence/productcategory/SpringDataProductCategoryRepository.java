package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.domain.productcategory.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SpringDataProductCategoryRepository extends JpaRepository<ProductCategoryJpaEntity, UUID> {
    List<ProductCategoryJpaEntity> findByIdCategoryId(UUID categoryId);
}
