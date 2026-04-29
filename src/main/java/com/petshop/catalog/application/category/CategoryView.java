package com.petshop.catalog.application.category;

import java.util.UUID;

public record CategoryView(
        UUID id,
        String name
) {}
