package com.petshop.catalog.infrastructure.persistence;

import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.domain.multimedia.MultimediaRepository;
import com.petshop.catalog.domain.product.ProductRepository;
import com.petshop.catalog.infrastructure.persistence.multimedia.MultimediaJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ImageStorageService {
    private final MultimediaRepository multimediaRepository;

    private final Path storageDir = Paths.get("images");

    public ImageStorageService(MultimediaRepository multimediaRepository) throws IOException {
        this.multimediaRepository = multimediaRepository;
        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }
    }
    public List<Multimedia> list() {
        return multimediaRepository.findAll();
    }

    public UUID saveImage(UUID ownerId, MultipartFile image, Boolean isPrimary) throws IOException {
        /* Genera nombre único: productId + extensión */
        String filename = ownerId + "-" + image.getOriginalFilename();
        Path filePath = storageDir.resolve(filename);

        /* Guarda el archivo */
        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        /* Lo creo en el dominio */
        final Multimedia dominio = Multimedia.create(ownerId, image.getOriginalFilename(), isPrimary);

        /* Lo convierto a entidad jpa */
        final MultimediaJpaEntity entidad = multimediaRepository.toEntity(dominio);

        /*  y lo guardo */
        multimediaRepository.save(entidad);

        // Retorna la "URL" de acceso (para este ejemplo, ruta relativa)
        return dominio.getId();
    }
    public void delete(UUID id) {
        multimediaRepository.deleteById(id);
    }
}
