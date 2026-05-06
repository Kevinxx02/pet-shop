package com.petshop.catalog.domain.product;

import com.petshop.catalog.domain.product.events.ProductCreated;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void should_create_product_and_raise_domain_event() {

        Product product = Product.create(
                "Producto Prueba",
                "descripcion",
                BigDecimal.valueOf(100)
        );

        assertNotNull(product);
        assertEquals("Producto Prueba", product.getName());
        assertEquals(1, product.pullDomainEvents().size());
        assertTrue(product.pullDomainEvents().get(0) instanceof ProductCreated);
    }
}