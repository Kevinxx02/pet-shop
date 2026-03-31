package com.petshop.catalog.web.category;

import com.petshop.catalog.application.category.CategoryView;
import com.petshop.catalog.application.category.GetCategoryService;
import com.petshop.catalog.application.category.CreateCategoryService;
import com.petshop.catalog.application.category.UpdateCategoryService;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<?> create(@RequestBody CreateCategoryRequest request) {
        final CategoryView category = createCategoryService.createCategory(
                request.name(),
                request.parentId()
        );

        final String message = "Categoria creada correctamente";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, category));
    }

    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody UpdateCategoryRequest request
    ) {
        final CategoryView category = updateCategoryService.updateCategory(
                request.id(),
                request.name(),
                request.parentId(),
                request.isVisible()
        );

        final String message = "Categoria actualizada correctamente";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, category));
    }
}
