package com.petshop.catalog.web;

import com.petshop.catalog.application.faq.FaqService;
import com.petshop.catalog.domain.faq.Faq;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/faq")
public class FaqController {
    /* El servicio */
    final FaqService faqService;

    /* Constructor */
    FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    /* Lo que devuelve cuando se usa el metodo get */
    @GetMapping
    public List<Faq> list() {
        return this.faqService.findAll();
    }
    /* Lo que hace cuando recibe el metodo POST */
    @PostMapping
    public UUID create(@RequestParam String question,
                       @RequestParam String answer) {
        return this.faqService.create(question, answer);
    }
    @PutMapping
    public UUID create(@RequestParam UUID id,
                       @RequestParam String question,
                       @RequestParam String answer) {
        return this.faqService.update(id, question, answer);
    }

    /* Lo que hace cuando recibe el metodo delete */
    @DeleteMapping("/{id}")
    public UUID delete(@PathVariable UUID id) {
        this.faqService.delete(id);
        return id;
    }
}
