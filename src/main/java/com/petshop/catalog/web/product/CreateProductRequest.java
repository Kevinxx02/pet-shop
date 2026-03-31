package com.petshop.catalog.web.product;
import java.math.BigDecimal;

public record CreateProductRequest(String name, String description, BigDecimal price) {}
