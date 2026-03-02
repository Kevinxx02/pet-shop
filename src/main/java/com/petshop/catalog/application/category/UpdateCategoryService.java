package com.petshop.catalog.application.category;

import com.petshop.catalog.domain.category.Category;
import com.petshop.catalog.domain.category.CategoryRepository;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UpdateCategoryService {
    private final CategoryRepository categoryRepository;

    public UpdateCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public UUID updateCategory(UUID id, String name, String imageName) {
        /* Obtiene el registro */
        CategoryJpaEntity entity = this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        final Boolean isCreator = false;
        /* Si el registro existe, crea un nuevo objeto en el dominio con el nuevo nombre y nueva imagen para validaciones internas */
        Category category = Category.rehydrate(id, name, imageName, isCreator);

        /* Guarda en el repositorio el nuevo objeto de dominio */
        entity.updateFrom(category);

        return category.getId();
    }
}
