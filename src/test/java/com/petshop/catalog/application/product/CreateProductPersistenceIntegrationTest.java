package com.petshop.catalog.application.product;

import com.petshop.catalog.infrastructure.persistence.outbox.SpringDataOutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.SpringDataProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@SpringBootTest
class CreateProductPersistenceIntegrationTest {
/* Hace lo mismo que el unit test pero sin usar Mocks, asi que es mas real */
    @Autowired
    CreateProductService service;

    @Autowired
    SpringDataProductRepository productRepository;

    @Autowired
    SpringDataOutboxRepository outboxRepository;

    @Test
    void should_create_product_and_persist_event() {
        /* Se obtiene el count antes de ejecutar el test para que no falle si la tabla tiene datos al inicio */
        final long OutboxCount = outboxRepository.count();

        final ProductView product = service.createProduct(
                "Producto",
                "desc",
                BigDecimal.valueOf(100)
        );

        final Optional<ProductJpaEntity> result = productRepository.findById(product.id());

        assertTrue(result.isPresent());
        assertEquals(OutboxCount + 1, outboxRepository.count());
    }
}