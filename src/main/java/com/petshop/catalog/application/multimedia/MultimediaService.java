package com.petshop.catalog.application.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.domain.multimedia.MultimediaReadRepository;
import com.petshop.catalog.domain.multimedia.MultimediaRepository;
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
public class MultimediaService {
    private final MultimediaRepository multimediaRepository;
    private final MultimediaReadRepository multimediaReadRepository;

    private final Path storageDir = Paths.get("images");

    public MultimediaService(
            MultimediaRepository multimediaRepository,
            MultimediaReadRepository multimediaReadRepository
    ) throws IOException {
        this.multimediaRepository = multimediaRepository;
        this.multimediaReadRepository = multimediaReadRepository;

        if (!Files.exists(storageDir)) {
            Files.createDirectories(storageDir);
        }
    }

    public List<MultimediaView> list() {
        return multimediaReadRepository.findAllView();
    }

    public String saveImage(
            UUID ownerId,
            MultipartFile file
    ) throws IOException {
        /* Genera nombre único: productId + extensión */
        String filename = ownerId + "-" + file.getOriginalFilename();
        Path filePath = storageDir.resolve(filename);

        /* Guarda el archivo */
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return file.getOriginalFilename();
    }

    public void delete(UUID id) {
        multimediaRepository.deleteById(id);
    }

    public MultimediaView add(
            UUID ownerId,
            MultipartFile file,
            Boolean isPrimary
    ) throws IOException {
        final String name = this.saveImage(ownerId, file);

        final Multimedia multimedia = Multimedia.create(ownerId, name, isPrimary);

        multimediaRepository.save(multimedia);

        return MultimediaMapper.toView(multimedia);
    }
}
