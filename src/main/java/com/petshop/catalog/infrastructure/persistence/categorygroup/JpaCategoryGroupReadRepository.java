package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroupReadRepository;
import com.petshop.catalog.application.categorygroup.CategoryGroupView;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaCategoryGroupReadRepository implements CategoryGroupReadRepository {

    private final SpringDataCategoryGroupReadRepository jpaRepository;

    public JpaCategoryGroupReadRepository(SpringDataCategoryGroupReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CategoryGroupView> findAll() {
        return this.jpaRepository.findActive();
    }
}
