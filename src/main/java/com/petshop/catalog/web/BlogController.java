package com.petshop.catalog.web;

import com.petshop.catalog.application.blog.BlogService;
import com.petshop.catalog.domain.blog.Blog;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blog")
public class BlogController {
    /* El servicio */
    final BlogService blogService;

    /* Constructor */
    BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /* Lo que devuelve cuando se usa el metodo get */
    @GetMapping
    public List<Blog> list() {
        return this.blogService.findAll();
    }
    /* Lo que hace cuando recibe el metodo POST */
    @PostMapping
    public UUID create(@RequestParam String title,
                       @RequestParam String date,
                       @RequestParam String url) {
        return this.blogService.create(title, date, url);
    }

    @PutMapping
    public UUID create(@RequestParam UUID id,
                       @RequestParam String title,
                       @RequestParam String date,
                       @RequestParam String url) {
        return this.blogService.update(id, title, date, url);
    }

    /* Lo que hace cuando recibe el metodo delete */
    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable UUID id) {
        this.blogService.delete(id);
        return id;
    }
}
