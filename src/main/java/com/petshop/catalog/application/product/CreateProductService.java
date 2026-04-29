package com.petshop.catalog.application.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.domain.product.events.ProductCreated;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductCreatedPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CreateProductService {

    private final ProductRepository productRepository;
    private final OutboxRepository outboxRepository;

    public CreateProductService(
            ProductRepository productRepository,
            OutboxRepository outboxRepository
    ) {
        this.productRepository = productRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public ProductView createProduct(String name, String description, BigDecimal price) {
        Product product = Product.create(name, description, price);
        productRepository.save(product);

        product.pullDomainEvents().forEach(event -> {
            if (event instanceof ProductCreated pc) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(
                            ProductCreatedPayload.create(pc.getProductId())
                    );

                    final String eventType = "Product created";

                    OutboxMessage outboxMessage = OutboxMessage.create(eventType, payload);

                    outboxRepository.save(outboxMessage);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return ProductMapper.toView(product);
    }
}
