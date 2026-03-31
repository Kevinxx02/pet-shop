package com.petshop.catalog.web.cartitem;

import java.util.UUID;

public record UpdateCartItemRequest(UUID id, int quantity) {}