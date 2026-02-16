package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.list.ProductView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ProductReadRepository {
    private final SpringDataProductReadRepository jpaRepository;
    public ProductReadRepository(SpringDataProductReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductView> findAll(Integer isAdmin){
        if (isAdmin == 1) {
            return this.jpaRepository.findAllProjected();
        }

        return this.jpaRepository.findAllVisible();
    }
}