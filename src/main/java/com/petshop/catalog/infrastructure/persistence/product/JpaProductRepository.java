package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
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
        entity.setImage(product.getImage());
        entity.setVisibility(product.getVisible());
        entity.setIsCreator(false);


        Set<ProductMultimediaJpaEntity> multimedia = product.getMultimedia()
                .stream()
                .map(productMultimedia -> {
                    ProductMultimediaJpaEntity mm = new ProductMultimediaJpaEntity();
                    mm.setId(productMultimedia.getId());
                    mm.setFileName(productMultimedia.getFileName());
                    mm.setProduct(entity);
                    return mm;
                })
                .collect(Collectors.toSet());

        entity.setMultimedia(multimedia);

        return entity;
    }
}