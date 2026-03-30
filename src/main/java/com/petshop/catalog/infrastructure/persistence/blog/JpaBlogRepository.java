package com.petshop.catalog.infrastructure.persistence.blog;

import com.petshop.catalog.domain.blog.BlogRepository;
import com.petshop.catalog.domain.blog.Blog;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaBlogRepository implements BlogRepository {

    private final SpringDataBlogRepository jpaRepository;

    public JpaBlogRepository(SpringDataBlogRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Blog blog) {
        this.jpaRepository.save(BlogMapper.toEntity(blog));
    }

    @Override
    public Optional<Blog> findById(UUID id) {
        return jpaRepository.findById(id).map(BlogMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }
}
