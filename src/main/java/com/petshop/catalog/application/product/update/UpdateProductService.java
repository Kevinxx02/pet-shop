package com.petshop.catalog.application.product.update;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.domain.product.events.ProductUpdated;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductUpdatedPayload;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final OutboxRepository outboxRepository;

    public UpdateProductService(ProductRepository productRepository,
                                OutboxRepository outboxRepository) {
        this.productRepository = productRepository;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public UUID updateProduct(UUID id, String name, String description, BigDecimal price) {
        Product product = Product.update(id, name, description, price);
        ProductJpaEntity entity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice().value());

        product.pullDomainEvents().forEach(event -> {
            if (event instanceof ProductUpdated productUpdated) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(
                            ProductUpdatedPayload.create(productUpdated.getProductId())
                    );

                    OutboxMessage outboxMessage = OutboxMessage.create("Product updated", payload);
                    outboxRepository.save(outboxMessage);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return product.getId();
    }
}
