package com.petshop.catalog.web;

import com.petshop.catalog.application.categorygroup.CategoryGroupService;
import com.petshop.catalog.application.categorygroup.CategoryGroupView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category-groups")
public class CategoryGroupController {
    final CategoryGroupService categoryGroupService;

    CategoryGroupController(CategoryGroupService categoryGroupService) {
        this.categoryGroupService = categoryGroupService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<CategoryGroupView>>> list() {
        final List<CategoryGroupView> categoryGroups = this.categoryGroupService.findAll();

        final String message = "Relacion entre categorias y grupos obtenidas";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, categoryGroups));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<CategoryGroupView>> create(
            @RequestParam UUID groupId,
            @RequestParam UUID categoryId) {
        final CategoryGroupView categoryGroup = this.categoryGroupService.create(groupId, categoryId);

        final String message = "Categoria asignada al grupo";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, categoryGroup));

    }

    @PutMapping
    public ResponseEntity<BaseResponse<CategoryGroupView>> update(
            @RequestParam UUID id,
            @RequestParam UUID groupId,
            @RequestParam UUID categoryId,
            @RequestParam boolean isActive
    ) {
        final CategoryGroupView categoryGroup = this.categoryGroupService.update(id, groupId, categoryId, isActive);

        final String message = "Relacion productos y categorias actualizada";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, categoryGroup));
    }
}
