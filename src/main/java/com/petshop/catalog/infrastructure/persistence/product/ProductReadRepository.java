package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.application.product.ProductView;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Repository
public class ProductReadRepository {
    private final SpringDataProductReadRepository jpaRepository;
    public ProductReadRepository(SpringDataProductReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductView> find(UUID id){
        if (!Objects.isNull(id)) {
            return this.jpaRepository.findByIdProjected(id);
        }

        final List<ProductView> response = this.jpaRepository.findVisibleProjected();
        return response;
    }

    public Boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}