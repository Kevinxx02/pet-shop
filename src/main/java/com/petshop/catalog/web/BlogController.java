package com.petshop.catalog.web;

import com.petshop.catalog.application.blog.BlogService;
import com.petshop.catalog.application.blog.BlogView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
/* Le falta el view y el mapper */
public class BlogController {
    /* El servicio */
    final BlogService blogService;

    /* Constructor */
    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /* Lo que devuelve cuando se usa el metodo get */
    @GetMapping
    public ResponseEntity<BaseResponse<List<BlogView>>> list() {
        final List<BlogView> lBlog = this.blogService.findAll();

        final String message = "Lista de publicaciones";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, lBlog));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<BlogView>> create(
            @RequestParam String title,
            @RequestParam String date,
            @RequestParam String url
    ) {

        final BlogView blog = this.blogService.create(title, date, url);

        final String message = "Publicacion creada";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, blog));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<BlogView>> update(
            @RequestParam UUID id,
            @RequestParam String title,
            @RequestParam String date,
            @RequestParam String url,
            @RequestParam boolean isVisible
    ) {

        final BlogView blog = this.blogService.update(id, title, date, url, isVisible);

        final String message = "Publicacion actualizada";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, blog));
    }
}
