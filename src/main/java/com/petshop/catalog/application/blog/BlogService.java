package com.petshop.catalog.application.blog;

import com.petshop.catalog.domain.blog.Blog;
import com.petshop.catalog.domain.blog.BlogRepository;
import com.petshop.catalog.infrastructure.persistence.blog.BlogJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlogService {
    /* Definir el repository del cual obtendra la informacion */
    final BlogRepository faqRepository;

    public BlogService(BlogRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    /* Definir metodos que se utilizan desde el contralador */

    /* Obtener todos */
    public List<Blog> findAll() {
        return this.faqRepository.findAll();
    }

    /* Crear una nueva vinculacion entre grupos de categorias */
    public UUID create(String title, String date, String url) {
        /* Crea la entidad en el dominio */
        final Blog dominio = Blog.create(title, date, url);

        /* Usa la entidad en el dominio para crear la entidad Jpa */
        final BlogJpaEntity entidad = faqRepository.toEntity(dominio);

        /* Guarda la entidad Jpa */
        faqRepository.save(entidad);

        /* Devuelve el ID */
        return dominio.getId();
    }

    /* Eliminar una categoria */
    public void delete(UUID id) {
        this.faqRepository.deleteById(id);
    }

    public UUID update(UUID id, String title, String date, String url) {
        BlogJpaEntity entity = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        final Blog dominio = Blog.rehydrate(id, title, date, url);
        entity.setTitle(dominio.getTitle());
        entity.setDate(dominio.getDate());
        entity.setUrl(dominio.getUrl());

        return  dominio.getId();
    }
}
