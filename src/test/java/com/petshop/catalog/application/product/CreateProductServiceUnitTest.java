package com.petshop.catalog.application.product;

import com.petshop.catalog.domain.product.ProductPrice;
import com.petshop.catalog.domain.product.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CreateProductServiceUnitTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CreateProductService service;

    @Mock
    DomainEventPublisher domainEventPublisher;

    @Test
    void should_create_product_and_outbox_event() {
        final String name = "Producto Prueba";
        final String description = "descripcion de producto prueba";
        final BigDecimal price = BigDecimal.valueOf(90);

        service.createProduct(name, description, price);

        verify(productRepository).save(argThat(product ->
                product.getName().equals(name) &&
                        product.getDescription().equals(description) &&
                        product.getPrice().equals(ProductPrice.from(price))
        ));

        verify(domainEventPublisher).publish(any());
    }
}