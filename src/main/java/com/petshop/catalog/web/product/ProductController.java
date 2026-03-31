package com.petshop.catalog.web.product;

import com.petshop.catalog.application.product.*;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponse<List<ProductView>>> list() {
        final List<ProductView> aProduct = listProductService.list();

        final String message = "Listado de productos";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, aProduct));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ProductView>> createProduct(
            @RequestBody CreateProductRequest request
    ) {

        ProductView product = createProductService.createProduct(
                request.name(),
                request.description(),
                request.price()
        );

        final String message = "Producto creado correctamente";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, product));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<ProductView>> updateProduct(
            @RequestBody UpdateProductRequest request) {
        final ProductView product = updateProductService.updateProduct(
                request.id(),
                request.name(),
                request.description(),
                request.price(),
                request.isVisible()
        );

        final String message = "Producto actualizado correctamente";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, product));
    }

}