package com.petshop.catalog.application.blog;

import com.petshop.catalog.domain.blog.Blog;

public class BlogMapper {
    public static BlogView toView(Blog blog) {
        return new BlogView(
                blog.getId(),
                blog.getTitle(),
                blog.getDate(),
                blog.getUrl()
        );
    }
}
