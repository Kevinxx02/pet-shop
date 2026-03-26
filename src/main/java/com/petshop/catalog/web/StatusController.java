package com.petshop.catalog.web;

import com.petshop.catalog.application.status.StatusService;
import com.petshop.catalog.application.status.StatusView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam String name) {
        final StatusView status = this.statusService.create(name);

        final String message = "Estatus creado correctamente";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, status));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<StatusView>>> list() {
        final List<StatusView> aStatus = this.statusService.viewAll();

        final String message = "Lista de estatus obtenida";
        return ResponseEntity.status(200).body(
                new BaseResponse<>(message, aStatus)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.statusService.delete(id);
    }
}
