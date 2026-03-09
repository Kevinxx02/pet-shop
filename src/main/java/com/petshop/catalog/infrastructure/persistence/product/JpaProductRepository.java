package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.infrastructure.persistence.multimedia.MultimediaJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JpaProductRepository implements ProductRepository {

    private final SpringDataProductRepository jpaRepository;

    public JpaProductRepository(SpringDataProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<ProductJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        jpaRepository.save(toEntity(product));
    }
    public void save(ProductJpaEntity product) {
        jpaRepository.save(product);
    }

    // Convertir de dominio a JPA
    private ProductJpaEntity toEntity(Product product) {
        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice().value());
        entity.setVisibility(product.getVisible());

        return entity;
    }
}