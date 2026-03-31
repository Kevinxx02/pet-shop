package com.petshop.catalog.web.blog;

import com.petshop.catalog.application.blog.BlogService;
import com.petshop.catalog.application.blog.BlogView;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponse<BlogView>> create(@RequestBody BlogCreateRequest request) {

        final BlogView blog = this.blogService.create(request.title(), request.date(), request.url());

        final String message = "Publicacion creada";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, blog));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<BlogView>> update(@RequestBody BlogUpdateRequest request
    ) {
        final BlogView blog = this.blogService.update(
                request.id(),
                request.title(),
                request.date(),
                request.url(),
                request.isVisible()
        );

        final String message = "Publicacion actualizada";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, blog));
    }
}
