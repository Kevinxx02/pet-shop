package com.petshop.catalog.domain.product;

import com.petshop.catalog.domain.DomainEvent;
import com.petshop.catalog.domain.product.events.ProductCreated;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductUnitTest {
/* Valida el funcionamiento del agregado Producto directamente */
    @Test
    void should_create_product_and_raise_domain_event() {

        Product product = Product.create(
                "Producto Prueba",
                "descripcion",
                BigDecimal.valueOf(100)
        );
        List<DomainEvent> events = product.pullDomainEvents();

        assertNotNull(product);
        assertEquals("Producto Prueba", product.getName());
        assertEquals(1, events.size());
        assertInstanceOf(ProductCreated.class, events.get(0));
    }
}