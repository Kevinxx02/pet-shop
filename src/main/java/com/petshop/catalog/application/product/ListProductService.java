package com.petshop.catalog.application.product;

import com.petshop.catalog.domain.product.ProductReadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListProductService {

    private final ProductReadRepository productRepository;

    public ListProductService(ProductReadRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductView> list() {
        return productRepository.findAllView();
    }
}
