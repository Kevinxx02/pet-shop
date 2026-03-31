package com.petshop.catalog.web.cart;


import java.util.UUID;

public record UpdateCartRequest(UUID id, UUID statusId) {}
