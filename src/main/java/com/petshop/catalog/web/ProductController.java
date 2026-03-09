package com.petshop.catalog.web;

import com.petshop.catalog.application.product.CreateProductService;
import com.petshop.catalog.application.product.ListProductService;
import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.application.product.UpdateProductService;
import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.infrastructure.persistence.ImageStorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final ImageStorageService imageStorageService;
    public ProductController(CreateProductService createProductService,
                             ListProductService listProductService,
                             UpdateProductService updateProductService, ImageStorageService imageStorageService) {
        this.createProductService = createProductService;
        this.listProductService = listProductService;
        this.updateProductService = updateProductService;
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public List<ProductView> list(@RequestParam(required = false) UUID id) {
        return listProductService.list(id);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam(defaultValue = "true") Boolean isVisible) throws IOException {
        UUID id = createProductService.createProduct(
                name,
                description,
                price,
                isVisible
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Producto creado correctamente";
            public final UUID productId = id;
        });
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProduct(
            @RequestParam UUID id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam Boolean isVisible
            ) throws IOException {
        updateProductService.updateProduct(
                id,
                name,
                description,
                price,
                isVisible
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Producto actualizado correctamente";
            public final UUID productId = id;
        });
    }

}