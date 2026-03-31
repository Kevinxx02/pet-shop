package com.petshop.catalog.web.cartitem;

import java.util.UUID;

public record CreateCartItemRequest(UUID cartId, UUID productId, int quantity) {}