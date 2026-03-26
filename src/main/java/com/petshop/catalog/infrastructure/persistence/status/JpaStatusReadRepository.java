package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.application.status.StatusView;
import com.petshop.catalog.domain.status.StatusReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaStatusReadRepository implements StatusReadRepository {

    private final SpringDataStatusReadRepository jpaRepository;

    public JpaStatusReadRepository(SpringDataStatusReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<StatusView> viewAll() {
        return this.jpaRepository.viewAll();
    }
}
