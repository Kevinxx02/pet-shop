package com.petshop.catalog.application.blog;

import com.petshop.catalog.domain.blog.Blog;
import com.petshop.catalog.domain.blog.BlogReadRepository;
import com.petshop.catalog.domain.blog.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BlogService {
    /* Definir el repository del cual obtendra la informacion */
    final BlogRepository blogRepository;
    final BlogReadRepository blogReadRepository;

    public BlogService(
            BlogRepository blogRepository,
            BlogReadRepository blogReadRepository
    ) {
        this.blogRepository = blogRepository;
        this.blogReadRepository = blogReadRepository;
    }

    /* Obtener todos */
    public List<BlogView> findAll() {
        return this.blogReadRepository.viewAll();
    }

    /* Crear una nueva vinculacion entre grupos de categorias */
    public BlogView create(String title, String date, String url) {
        final Blog blog = Blog.create(title, date, url);

        this.blogRepository.save(blog);

        return BlogMapper.toView(blog);
    }

    public BlogView update(
            UUID id,
            String title,
            String date,
            String url,
            boolean isVisible
    ) {
        if(!this.blogRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        /* El save debe guardar dominio */
        final Blog blog = Blog.rehydrate(id, title, date, url, isVisible);

        this.blogRepository.save(blog);

        return BlogMapper.toView(blog);
    }
}
