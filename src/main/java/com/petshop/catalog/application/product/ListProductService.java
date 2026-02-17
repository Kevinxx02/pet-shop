package com.petshop.catalog.application.product;

import com.petshop.catalog.infrastructure.persistence.product.ProductReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ListProductService {

    private final ProductReadRepository productRepository;

    public ListProductService(ProductReadRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductView> list(UUID id, Integer isAdmin) {
        return productRepository.find(id, isAdmin);
    }
}
