package com.petshop.catalog.web;

import com.petshop.catalog.application.product.create.CreateProductCommand;
import com.petshop.catalog.application.product.create.CreateProductService;
import com.petshop.catalog.application.product.list.ListProductService;
import com.petshop.catalog.application.product.list.ProductView;
import com.petshop.catalog.application.product.update.UpdateProductService;
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
    public List<ProductView> list(@RequestParam(defaultValue = "0") Integer isAdmin) {
        return listProductService.list(isAdmin);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(
            @RequestParam String name,
            @RequestParam BigDecimal price) {
        UUID id = createProductService.createProduct(
                name,
                "description",
                price,
                "file"
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
            @RequestParam BigDecimal price,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam Boolean isVisible
            ) throws IOException {

        UUID response = updateProductService.updateProduct(
                id,
                name,
                "description()",
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