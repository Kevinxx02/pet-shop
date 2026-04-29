package com.petshop.catalog.application.product;

import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CreateProductService {

    private final ProductRepository productRepository;
    private final DomainEventPublisher domainEventPublisher;

    public CreateProductService(
            ProductRepository productRepository,
            DomainEventPublisher domainEventPublisher
    ) {
        this.productRepository = productRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Transactional
    public ProductView createProduct(String name, String description, BigDecimal price) {
        Product product = Product.create(name, description, price);
        productRepository.save(product);

        product.pullDomainEvents().forEach(domainEventPublisher::publish);

        return ProductMapper.toView(product);
    }
}
