package com.petshop.catalog.application.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroup;
import com.petshop.catalog.domain.categorygroup.CategoryGroupRepository;
import com.petshop.catalog.infrastructure.persistence.categorygroup.CategoryGroupJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryGroupService {
    /* Definir el repository del cual obtendra la informacion */
    final CategoryGroupRepository categoryGroupRepository;

    public CategoryGroupService(CategoryGroupRepository categoryGroupRepository) {
        this.categoryGroupRepository = categoryGroupRepository;
    }

    /* Definir metodos que se utilizan desde el contralador */

    /* Obtener todos */
    public List<CategoryGroup> findAll() {
        return this.categoryGroupRepository.findAll();
    }

    /* Crear una nueva vinculacion entre grupos de categorias */
    public UUID create(String name, UUID categoryId) {
        /* Crea la entidad en el dominio */
        final CategoryGroup dominio = CategoryGroup.create(name, categoryId);

        /* Usa la entidad en el dominio para crear la entidad Jpa */
        final CategoryGroupJpaEntity entidad = categoryGroupRepository.toEntity(dominio);

        /* Guarda la entidad Jpa */
        categoryGroupRepository.save(entidad);

        /* Devuelve el ID */
        return dominio.getId();
    }

    /* Eliminar una categoria */
    public void delete(UUID id) {
        this.categoryGroupRepository.deleteById(id);
    }
}
