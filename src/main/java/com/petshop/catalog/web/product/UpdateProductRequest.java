package com.petshop.catalog.web.product;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductRequest(UUID id, String name, String description, BigDecimal price, boolean isVisible){}
