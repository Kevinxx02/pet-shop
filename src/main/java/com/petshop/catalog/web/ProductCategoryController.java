package com.petshop.catalog.web;

import com.petshop.catalog.application.product.ProductView;
import com.petshop.catalog.application.productcategory.ProductCategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {
    final ProductCategoryService productCategoryService;
    ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/products/byCategory/{categoryId}")
    public List<ProductView> getProductsFromCategory(@PathVariable UUID categoryId) {
        return this.productCategoryService.listProductsFromCategory(categoryId);
    }

    @PutMapping("/product")
    public Boolean updateCategoriesFromProduct(@RequestParam UUID productId, @RequestParam List<UUID> categories) {
        return this.productCategoryService.updateCategoriesFromProduct(productId, categories);
    }
}
