package com.petshop.catalog.infrastructure.persistence.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataProductCategoryRepository extends JpaRepository<ProductCategoryJpaEntity, UUID> {
    /* Elimina todos los registros cuyo product id sea el parametro recibido */
    void deleteById(UUID id);

    boolean existsByProduct_IdAndCategory_Id(UUID productId, UUID categoryId);
}
