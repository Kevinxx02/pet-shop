package com.petshop.catalog.web;

import com.petshop.catalog.application.category.CategoryView;
import com.petshop.catalog.application.category.GetCategoryService;
import com.petshop.catalog.application.category.CreateCategoryService;
import com.petshop.catalog.application.category.UpdateCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final GetCategoryService getCategoryService;
    private final CreateCategoryService createCategoryService;
    private final UpdateCategoryService updateCategoryService;

    public CategoryController(
            GetCategoryService getCategoryService,
            CreateCategoryService createCategoryService,
            UpdateCategoryService updateCategoryService
    ) {
        this.getCategoryService = getCategoryService;
        this.createCategoryService = createCategoryService;
        this.updateCategoryService = updateCategoryService;
    }

    @GetMapping
    public List<CategoryView> list() {
        return getCategoryService.list();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam String name,
                       @RequestParam(required = false) UUID parentId,
                       @RequestParam(defaultValue = "true") Boolean isVisible) throws IOException {
        final CategoryView category = createCategoryService.createCategory(name, parentId, isVisible);

        final String message = "Categoria creada correctamente";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, category));
    }

    @PutMapping
    public ResponseEntity<?> update(
        @RequestParam UUID id,
        @RequestParam String name,
        @RequestParam(required = false) UUID parentId,
        @RequestParam Boolean isVisible
    ) throws IOException {
        final CategoryView category = updateCategoryService.updateCategory(id, name, parentId, isVisible);

        final String message = "Categoria actualizada correctamente";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, category));
    }
}
