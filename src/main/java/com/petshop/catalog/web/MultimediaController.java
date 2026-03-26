package com.petshop.catalog.web;

import com.petshop.catalog.application.multimedia.MultimediaView;
import com.petshop.catalog.application.multimedia.MultimediaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/multimedia")
public class MultimediaController {
    private final MultimediaService multimediaService;

    public MultimediaController(MultimediaService multimediaService) {
        this.multimediaService = multimediaService;
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<MultimediaView>>> list() {
        final List<MultimediaView> aMultimedia = multimediaService.list();

        final String message = "Relacion con archivos multimedia obtenidos";
        return ResponseEntity.status(200).body(
                new BaseResponse<>(message, aMultimedia)
        );
    }

    @PostMapping
    public ResponseEntity<BaseResponse<MultimediaView>> add(
                @RequestParam UUID ownerId,
                @RequestParam MultipartFile file,
                @RequestParam  Boolean isPrimary
    ) throws IOException {
        final MultimediaView multimedia = this.multimediaService.add(ownerId, file, isPrimary);

        final String message = "Archivo multimedia agregado";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, multimedia));
    }
/* A este metodo le falta, se supone que si elimino, primero debo verificar que existe y ya que es multimedia deberia eliminar el archivo tambien */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.multimediaService.delete(id);
    }
}
