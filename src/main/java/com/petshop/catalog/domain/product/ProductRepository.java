package com.petshop.catalog.domain.product;

import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface ProductRepository {
    Optional<ProductJpaEntity> findById(UUID id);
    void save(Product product);
    void save(ProductJpaEntity product);
}
