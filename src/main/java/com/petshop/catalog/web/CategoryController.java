package com.petshop.catalog.web;

import com.petshop.catalog.application.category.GetCategoryService;
import com.petshop.catalog.application.category.CreateCategoryService;
import com.petshop.catalog.application.category.UpdateCategoryService;
import com.petshop.catalog.application.productcategory.ProductCategoryService;
import com.petshop.catalog.domain.category.Category;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final GetCategoryService getCategoryService;
    private final CreateCategoryService createCategoryService;
    private final UpdateCategoryService updateCategoryService;
    private final ProductCategoryService productCategoryService;

    public CategoryController(GetCategoryService getCategoryService, CreateCategoryService createCategoryService, UpdateCategoryService updateCategoryService, ProductCategoryService productCategoryService) {
        this.getCategoryService = getCategoryService;
        this.createCategoryService = createCategoryService;
        this.updateCategoryService = updateCategoryService;
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public List<Category> list(@RequestParam(required = false) UUID id,
                               @RequestParam(defaultValue = "0") Integer isAdmin) {
        return getCategoryService.list();
    }

    @PostMapping
    public UUID create(@RequestParam String name,
                       @RequestParam String imageName) throws IOException {
        return createCategoryService.createCategory(name, imageName);
    }

    @PutMapping
    public UUID update( @RequestParam UUID id,
                        @RequestParam String name,
                        @RequestParam String imageName) throws IOException {
        return updateCategoryService.updateCategory(id, name, imageName);
    }
}
