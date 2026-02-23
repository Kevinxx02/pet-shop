package com.petshop.catalog.infrastructure.seeder;

import com.petshop.catalog.domain.category.CategoryRepository;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.domain.user.UserRepository;
import com.petshop.catalog.infrastructure.persistence.category.CategoryJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import com.petshop.catalog.infrastructure.persistence.user.UserJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private static final UUID DEFAULT_ID =
            UUID.fromString("00000000-0000-0000-0000-000000000001");

    @Override
    public void run(String... args) {

        if (productRepository.findById(DEFAULT_ID).isEmpty()) {

            ProductJpaEntity entity = new ProductJpaEntity();
            entity.setId(DEFAULT_ID);
            entity.setName("Nuevo producto");
            entity.setDescription("");
            entity.setPrice(BigDecimal.ZERO);
            entity.setVisibility(false);
            entity.setIsCreator(true);
            entity.setImage("agregar.png");
            productRepository.save(entity);
        }

        if (categoryRepository.findById(DEFAULT_ID).isEmpty()) {
            CategoryJpaEntity entity = new CategoryJpaEntity();
            entity.setId(DEFAULT_ID);
            entity.setName("Nueva categoria");
            entity.setImageName("agregar.png");
            categoryRepository.save(entity);
        }

        if (userRepository.findById(DEFAULT_ID).isEmpty()) {
            UserJpaEntity entity = new UserJpaEntity();
            entity.setId(DEFAULT_ID);
            entity.setName("Nuevo usuario");
            entity.setPassword("");
            entity.setDeleted(false);
            userRepository.save(entity);
        }
    }
}
