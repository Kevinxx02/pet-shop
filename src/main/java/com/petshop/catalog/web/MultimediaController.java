package com.petshop.catalog.web;

import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.infrastructure.persistence.ImageStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {
    private final ImageStorageService imageStorageService;

    public MultimediaController(ImageStorageService imageStorageService) {
        this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public List<Multimedia> list() {
        return imageStorageService.list();
    }

    @PostMapping
    public UUID add(
                @RequestParam UUID ownerId,
                @RequestParam MultipartFile file,
                @RequestParam  Boolean isPrimary
    ) throws IOException {
        return this.imageStorageService.saveImage(ownerId, file, isPrimary);
    }

    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable UUID id) {
        this.imageStorageService.delete(id);
        return id;
    }
}
