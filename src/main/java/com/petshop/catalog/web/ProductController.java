package com.petshop.catalog.web;

import com.petshop.catalog.application.product.create.CreateProductService;
import com.petshop.catalog.application.product.list.ListProductService;
import com.petshop.catalog.application.product.list.ProductView;
import com.petshop.catalog.application.product.update.UpdateProductCommand;
import com.petshop.catalog.application.product.update.UpdateProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public static class CreateProductRequest {
        public String name;
        public String description;
        public BigDecimal price;
    }


    @GetMapping
    public List<ProductView> list() {
        return listProductService.list();
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest request) {
        UUID id = createProductService.createProduct(
                request.name,
                request.description,
                request.price
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Producto creado correctamente";
            public final UUID productId = id;
        });
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductCommand request) {
        UUID id = updateProductService.updateProduct(
                request.id(),
                request.name(),
                request.description(),
                request.price()
        );

        return ResponseEntity.ok(new Object() {
            public final String message = "Producto actualizado correctamente";
            public final UUID productId = id;
        });
    }
}