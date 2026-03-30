package com.petshop.catalog.infrastructure.persistence.blog;

import com.petshop.catalog.domain.blog.Blog;

public class BlogMapper {
    public static Blog toDomain(BlogJpaEntity entity) {
        return Blog.rehydrate(
                entity.getId(),
                entity.getTitle(),
                entity.getDate(),
                entity.getUrl(),
                entity.getVisible()
        );
    }

    public static BlogJpaEntity toEntity(Blog blog) {
        return new BlogJpaEntity(
                blog.getId(),
                blog.getTitle(),
                blog.getDate(),
                blog.getUrl(),
                blog.getVisible()
        );
    }
}
