package com.petshop.catalog.web.productcategory;

import com.petshop.catalog.application.productcategory.ProductCategoryService;
import com.petshop.catalog.application.productcategory.ProductCategoryView;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product-categories")
public class ProductCategoryController {
    final ProductCategoryService productCategoryService;
    ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<ProductCategoryView>>> list() {
        final List<ProductCategoryView> productCategories = productCategoryService.findAll();

        final String message = "Relacion productos y categorias obtenidas";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, productCategories));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<ProductCategoryView>> create(
            @RequestBody CreateProductCategoryRequest request
    ) {
        final ProductCategoryView productCategory = productCategoryService.create(
                request.productId(),
                request.categoryId()
        );

        final String message = "Relacion productos y categorias creado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, productCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productCategoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
