package com.petshop.catalog.web;

import com.petshop.catalog.application.product.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;
    private final ListProductService listProductService;

    public ProductController(CreateProductService createProductService,
                             ListProductService listProductService,
                             UpdateProductService updateProductService) {
        this.createProductService = createProductService;
        this.listProductService = listProductService;
        this.updateProductService = updateProductService;
    }

    @GetMapping
    public ResponseEntity<?> list() {
        final List<ProductView> aProduct = listProductService.list();

        final String message = "Listado de productos";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, aProduct));
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price) {

        ProductView product = createProductService.createProduct(
                name,
                description,
                price
        );

        final String message = "Producto creado correctamente";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, product));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(
            @RequestParam UUID id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Boolean isVisible
            ) {
        final ProductView product = updateProductService.updateProduct(
                id,
                name,
                description,
                price,
                isVisible
        );

        final String message = "Producto actualizado correctamente";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, product));
    }

}