package com.petshop.catalog.web;

import com.petshop.catalog.application.product.create.CreateProductService;
import com.petshop.catalog.application.product.list.ListProductService;
import com.petshop.catalog.application.product.list.ProductView;
import com.petshop.catalog.domain.product.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductService createProductService;
    private final ListProductService listProductService;

    public ProductController(CreateProductService createProductService,
                             ListProductService listProductService) {
        this.createProductService = createProductService;
        this.listProductService = listProductService;
    }

    public static class CreateProductRequest {
        public String name;
        public String description;
        public BigDecimal price;
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        UUID id = createProductService.createProduct(
                request.name,
                request.description,
                request.price
        );

        return ResponseEntity.ok(new Object() {
            public String message = "Producto creado correctamente";
            public UUID productId = id;
        });
    }

    @GetMapping
    public List<ProductView> list() {
        return listProductService.list();
    }
}