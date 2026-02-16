package com.petshop.catalog.infrastructure.persistence;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageService {

    private final Path storageDir = Paths.get("images");

    public ImageStorageService() throws IOException {
        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }
    }

    public String saveImage(MultipartFile image, UUID productId) throws IOException {
        // Genera nombre único: productId + extensión
        String filename = productId + "-" + image.getOriginalFilename();
        Path filePath = storageDir.resolve(filename);

        // Guarda archivo
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Retorna la "URL" de acceso (para este ejemplo, ruta relativa)
        return "/images/" + filename;
    }
}
