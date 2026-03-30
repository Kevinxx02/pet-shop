package com.petshop.catalog.domain.blog;

import com.petshop.catalog.application.blog.BlogView;

import java.util.List;

public interface BlogReadRepository {
    List<BlogView> viewAll();
}
