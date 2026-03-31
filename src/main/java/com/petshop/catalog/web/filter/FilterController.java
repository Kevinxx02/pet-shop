package com.petshop.catalog.web.filter;

import com.petshop.catalog.application.filter.FilterService;
import com.petshop.catalog.application.filter.FilterView;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filters")
public class FilterController {
    private final FilterService filterService;

    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<FilterView>>> getFilters() {
        final List<FilterView> filters = this.filterService.viewAll();

        final String message = "Listado de filtros";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, filters));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<FilterView>> createFilter(@RequestBody CreateFilterRequest request) {
        final FilterView filter = this.filterService.create(
                request.parentId(),
                request.objectId()
        );

        final String message = "Objeto agregado a filtro";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, filter));
    }

    @DeleteMapping
    public void deleteFilter(@RequestBody DeleteFilterRequest request) {
        this.filterService.delete(request.id());
    }
}
