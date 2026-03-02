package com.petshop.catalog.domain.category;

import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface CategoryRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(Category user);
    void save(CategoryJpaEntity user);

    List<Category> findVisible();
    Optional<CategoryJpaEntity> findById(UUID Id);

    Boolean existsById(UUID categoryId);
}
