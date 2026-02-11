package com.petshop.catalog.domain.product;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface ProductRepository {
    Optional<Product> findById(UUID id);
    void save(Product product);
    List<Product> findAll();
}
