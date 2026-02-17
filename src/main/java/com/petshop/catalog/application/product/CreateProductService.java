package com.petshop.catalog.application.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.events.ProductCreated;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.infrastructure.persistence.ImageStorageService;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxMessage;
import com.petshop.catalog.infrastructure.persistence.outbox.OutboxRepository;
import com.petshop.catalog.infrastructure.persistence.product.ProductCreatedPayload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class CreateProductService {

    private final ProductRepository productRepository;
    private final OutboxRepository outboxRepository;
    private final ImageStorageService imageStorageService;

    public CreateProductService(ProductRepository productRepository,
                                ImageStorageService imageStorageService,
                                OutboxRepository outboxRepository) {
        this.productRepository = productRepository;
        this.outboxRepository = outboxRepository;
        this.imageStorageService = imageStorageService;
    }

    @Transactional
    public UUID createProduct(String name, String description, BigDecimal price, MultipartFile image, Boolean isVisible) throws IOException {
        Product product = Product.create(name, description, price, isVisible);
        product.setImage(image.getOriginalFilename());

        productRepository.save(product);

        imageStorageService.saveImage(image, product.getId());

        product.pullDomainEvents().forEach(event -> {
            if (event instanceof ProductCreated pc) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    String payload = mapper.writeValueAsString(
                            ProductCreatedPayload.create(pc.getProductId())
                    );

                    OutboxMessage outboxMessage = OutboxMessage.create("Product created", payload);
                    outboxRepository.save(outboxMessage);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return product.getId();
    }
}
