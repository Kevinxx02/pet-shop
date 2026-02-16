package com.petshop.catalog.application.product.update;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductCommand(
        UUID id,
        String name,
        BigDecimal price,
        String image,
        MultipartFile file
) {
}