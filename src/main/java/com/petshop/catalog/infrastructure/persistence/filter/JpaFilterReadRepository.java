package com.petshop.catalog.infrastructure.persistence.filter;

import com.petshop.catalog.application.filter.FilterView;
import com.petshop.catalog.domain.filter.FilterReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFilterReadRepository implements FilterReadRepository {

    private final SpringDataFilterReadRepository jpaRepository;

    public JpaFilterReadRepository(SpringDataFilterReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<FilterView> viewAll() {
        return this.jpaRepository.viewAll();
    }
}
