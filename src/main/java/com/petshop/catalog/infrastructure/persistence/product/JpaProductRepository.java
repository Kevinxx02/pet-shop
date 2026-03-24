package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository jpaRepository;

    public JpaProductRepository(SpringDataProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return jpaRepository.findById(id).map(ProductMapper::toDomain);
    }

    @Override
    public void save(Product product) {
        jpaRepository.save(ProductMapper.toEntity(product));
    }
}