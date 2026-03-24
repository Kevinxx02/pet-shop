package com.petshop.catalog.infrastructure.persistence.category;

import com.petshop.catalog.application.category.CategoryView;
import com.petshop.catalog.domain.category.CategoryReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCategoryReadRepository implements CategoryReadRepository {
    private final SpringDataCategoryReadRepository jpaRepository;

    public JpaCategoryReadRepository(SpringDataCategoryReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CategoryView> findVisible() {
        return jpaRepository.findVisible();
    }
}
