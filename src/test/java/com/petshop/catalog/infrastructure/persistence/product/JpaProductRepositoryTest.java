package com.petshop.catalog.infrastructure.persistence.product;

import com.petshop.catalog.domain.product.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@DataJpaTest
@Import(JpaProductRepository.class)
class JpaProductRepositoryTest {
/* Valida que al guardar en la base de datos, el nombre sigue siendo el mismo */
    @Autowired
    JpaProductRepository repository;

    @Test
    void should_save_and_load_product() {

        Product product = Product.create(
                "Producto Test",
                "desc",
                BigDecimal.valueOf(100)
        );

        repository.save(product);

        var result = repository.findById(product.getId());

        assertTrue(result.isPresent());
        assertEquals("Producto Test", result.get().getName());
    }
}