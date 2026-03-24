package com.petshop.catalog.application.product;


import com.petshop.catalog.domain.product.Product;

public class ProductMapper {
    static ProductView toView(Product domain) {
        return new ProductView(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getPrice().value()
        );
    }
}
