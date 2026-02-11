package com.petshop.catalog.application.product.create;

import com.petshop.catalog.domain.DomainEvent;
import com.petshop.catalog.domain.product.Product;

import java.util.List;

public class CreateProductResponse {
    public Product product;
    public List<DomainEvent> domainEvents;
}
