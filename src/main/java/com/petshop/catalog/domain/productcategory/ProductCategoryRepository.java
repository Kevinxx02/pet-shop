package com.petshop.catalog.domain.productcategory;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface ProductCategoryRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(ProductCategory productCategory);
    boolean existsById(UUID id);
    boolean existsByProductIdAndCategoryId(UUID productId, UUID categoryId);
    void deleteById(UUID productId);
}
