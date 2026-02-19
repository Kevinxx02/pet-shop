package com.petshop.catalog.domain.category;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryRepository {
    /* Method Overload para que funcione correctamente sin importar el tipo de parametro que se le envie */
    void save(Category user);

    List<Category> findAll();
}
