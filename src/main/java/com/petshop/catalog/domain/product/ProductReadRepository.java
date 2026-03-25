package com.petshop.catalog.domain.product;

import com.petshop.catalog.application.product.ProductView;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface ProductReadRepository {
    boolean existsById(UUID productId);

    List<ProductView> findAllView();
}
