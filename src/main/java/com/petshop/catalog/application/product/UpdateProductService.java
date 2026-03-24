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
public class UpdateProductService {

    private final ProductRepository productRepository;

    public UpdateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductView updateProduct(UUID id, String name, String description, BigDecimal price, boolean isVisible) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

        product.update(name, description, price, isVisible);

        this.productRepository.save(product);

        return ProductMapper.toView(product);
    }
}
