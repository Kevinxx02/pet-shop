package com.petshop.catalog.infrastructure.persistence.blog;

import com.petshop.catalog.domain.blog.BlogRepository;
import com.petshop.catalog.domain.blog.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaBlogRepository implements BlogRepository {

    private final SpringDataBlogRepository jpaRepository;

    public JpaBlogRepository(SpringDataBlogRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(BlogJpaEntity entity) {
        jpaRepository.save(entity);
    }

    @Override
    public BlogJpaEntity toEntity(Blog blog) {
        BlogJpaEntity entity = new BlogJpaEntity();
        entity.setId(blog.getId());
        entity.setTitle(blog.getTitle());
        entity.setDate(blog.getDate());
        entity.setUrl(blog.getUrl());

        return entity;
    }

    @Override
    public Blog toDomain(BlogJpaEntity entity) {
        return Blog.rehydrate(entity.getId(), entity.getTitle(), entity.getDate(), entity.getUrl());
    }

    @Override
    public List<Blog> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<BlogJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }
}
