package com.petshop.catalog.infrastructure.persistence.blog;

import com.petshop.catalog.application.blog.BlogView;
import com.petshop.catalog.domain.blog.BlogReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBlogReadRepository implements BlogReadRepository {

    private final SpringDataBlogReadRepository jpaRepository;

    public JpaBlogReadRepository(SpringDataBlogReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<BlogView> viewAll() {
        return this.jpaRepository.viewAll();
    }
}
