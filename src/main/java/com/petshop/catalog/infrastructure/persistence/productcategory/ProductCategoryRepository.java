package com.petshop.catalog.infrastructure.persistence.productcategory;

import com.petshop.catalog.domain.productcategory.ProductCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductCategoryRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(ProductCategory productCategory);

    List<ProductCategory> findAll();

    List<ProductCategoryJpaEntity> findByCategoryId(UUID categoryId);
}
