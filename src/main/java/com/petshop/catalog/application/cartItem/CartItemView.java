package com.petshop.catalog.application.cartItem;


import java.math.BigDecimal;
import java.util.UUID;

public record CartItemView(UUID id, UUID productId, int quantity, BigDecimal unitPrice) {}
