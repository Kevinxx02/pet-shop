package com.petshop.catalog.web;

import com.petshop.catalog.application.product.CreateProductService;
import com.petshop.catalog.application.product.ListProductService;
import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.application.product.UpdateProductService;
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

    public ProductController(CreateProductService createProductService,
                             ListProductService listProductService,
                             UpdateProductService updateProductService) {
        this.createProductService = createProductService;
        this.listProductService = listProductService;
        this.updateProductService = updateProductService;
    }

    @GetMapping
    public List<ProductView> list(@RequestParam(required = false) UUID id,
                                  @RequestParam(defaultValue = "0") Integer isAdmin) {
        return listProductService.list(id, isAdmin);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam BigDecimal price,
            @RequestParam MultipartFile file,
            @RequestParam Boolean isVisible) throws IOException {
        UUID id = createProductService.createProduct(
                name,
                description,
                price,
                file,
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
            /* Al actualizar no es requerido que suban nuevamente la imagen */
            @RequestParam(required = false) MultipartFile file,
            @RequestParam Boolean isVisible
            ) throws IOException {
        updateProductService.updateProduct(
                id,
                name,
                description,
                price,
                file,
                isVisible
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Producto actualizado correctamente";
            public final UUID productId = id;
        });
    }
}