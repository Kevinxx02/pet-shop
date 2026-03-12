package com.petshop.catalog.web;

import com.petshop.catalog.application.productcategory.ProductCategoryService;
import com.petshop.catalog.domain.productcategory.ProductCategory;
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
    public List<ProductCategory> list() {
        return productCategoryService.findAll();
    }
    @PostMapping
    public UUID create(@RequestParam UUID productId, @RequestParam UUID categoryId) {
        return productCategoryService.create(productId, categoryId);
    }
    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable UUID id) {
        productCategoryService.delete(id);

        return id;
    }
}
