package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.domain.product.ProductReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaProductReadRepository implements ProductReadRepository  {
    private final SpringDataProductReadRepository jpaRepository;
    public JpaProductReadRepository(SpringDataProductReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<ProductView> findAllView() {
        return jpaRepository.findAllView();
    }
}