package com.petshop.catalog.application.product;

import com.petshop.catalog.infrastructure.persistence.outbox.OutboxEventJpaEntity;
import com.petshop.catalog.infrastructure.persistence.outbox.SpringDataOutboxRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CreateProductOutboxIntegrationTest {
/* Hace lo mismo que el Persistence Integration Test, pero se asegura de que el evento se haya registrado en la base de datos de la forma esperada */
    @Autowired
    CreateProductService service;

    @Autowired
    SpringDataOutboxRepository outboxRepository;

    @Test
    void should_persist_outbox_message_when_product_is_created() {
        /* Se obtiene el count antes de ejecutar el test para que no falle si la tabla tiene datos al inicio */
        final long OutboxCount = outboxRepository.count();

        service.createProduct(
                "Producto",
                "descripcion",
                BigDecimal.valueOf(100)
        );

        assertEquals(OutboxCount + 1, outboxRepository.count());

        final OutboxEventJpaEntity message =
                outboxRepository.findAll().get(0);

        /* Solo funcionara si el ultimo evento creado es el product created */
        assertEquals(
                "ProductCreated",
                message.getEventType()
        );
    }
}