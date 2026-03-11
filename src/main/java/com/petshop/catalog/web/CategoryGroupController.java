package com.petshop.catalog.web;

import com.petshop.catalog.application.categorygroup.CategoryGroupService;
import com.petshop.catalog.domain.categorygroup.CategoryGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category-group")
public class CategoryGroupController {
    /* El servicio */
    final CategoryGroupService categoryGroupService;

    /* Constructor */
    CategoryGroupController(CategoryGroupService categoryGroupService) {
        this.categoryGroupService = categoryGroupService;
    }

    /* Lo que devuelve cuando se usa el metodo get */
    @GetMapping
    public List<CategoryGroup> list() {
        return this.categoryGroupService.findAll();
    }
    /* Lo que hace cuando recibe el metodo POST */
    @PostMapping
    public UUID create(@RequestParam String name, @RequestParam UUID categoryId) {
        return this.categoryGroupService.create(name, categoryId);
    }

    /* Lo que hace cuando recibe el metodo delete */
    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable UUID id) {
        this.categoryGroupService.delete(id);
        return id;
    }
}
