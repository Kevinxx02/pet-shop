package com.petshop.catalog.application.product;

import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public UUID createProduct(String name, String description, BigDecimal price, Boolean isVisible) throws IOException {
        Product product = Product.create(name, description, price, isVisible);
        productRepository.save(product);
        return product.getId();
    }
}
