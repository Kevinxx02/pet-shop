package com.petshop.catalog.infrastructure.persistence;

import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    private static final UUID DEFAULT_PRODUCT_ID =
            UUID.fromString("00000000-0000-0000-0000-000000000001");

    @Override
    public void run(String... args) {

        if (productRepository.findById(DEFAULT_PRODUCT_ID).isEmpty()) {

            ProductJpaEntity entity = new ProductJpaEntity();
            entity.setId(DEFAULT_PRODUCT_ID);
            entity.setName("Nuevo producto");
            entity.setDescription("");
            entity.setPrice(BigDecimal.ZERO);
            entity.setVisibility(true);
            entity.setIsCreator(true);
            productRepository.save(entity);
        }
    }
}
