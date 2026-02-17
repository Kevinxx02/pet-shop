package com.petshop.catalog.application.product.list;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductView (
        UUID id,
        String name,
        String description,
        BigDecimal price,
        String image,
        Boolean isVisible,
        Boolean isCreator
) {}
