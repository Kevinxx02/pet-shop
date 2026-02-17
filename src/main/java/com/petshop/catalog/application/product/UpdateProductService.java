package com.petshop.catalog.application.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.domain.product.events.ProductUpdated;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.JpaProductRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductUpdatedPayload;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.petshop.catalog.infrastructure.persistence.ImageStorageService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
public class UpdateProductService {

    private final ProductRepository productRepository;
    private final OutboxRepository outboxRepository;
    private final ImageStorageService imageStorageService;
    private final JpaProductRepository jpaProductRepository;

    public UpdateProductService(ProductRepository productRepository,
                                OutboxRepository outboxRepository,
                                ImageStorageService imageStorageService,
                                JpaProductRepository jpaProductRepository) {
        this.productRepository = productRepository;
        this.outboxRepository = outboxRepository;
        this.imageStorageService = imageStorageService;
        this.jpaProductRepository = jpaProductRepository;
    }

    @Transactional
    public UUID updateProduct(UUID id, String name, String description, BigDecimal price, MultipartFile image, Boolean isVisible) throws IOException {
        ProductJpaEntity entity = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        Product product = Product.update(id, name, description, price, isVisible);

        if (!Objects.isNull(image)) {
            imageStorageService.saveImage(image, id);
            product.setImage(image.getOriginalFilename());
        }

        entity.updateFrom(product);

        product.pullDomainEvents().forEach(event -> {
            if (event instanceof ProductUpdated productUpdated) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(
                            new ProductUpdatedPayload(productUpdated.getProductId())
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
