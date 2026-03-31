package com.petshop.catalog.web.faq;

import com.petshop.catalog.application.faq.FaqService;
import com.petshop.catalog.application.faq.FaqView;
import com.petshop.catalog.web.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<BaseResponse<List<FaqView>>> list() {
        final List<FaqView> faqs = this.faqService.viewAll();

        final String message = "Lista de preguntas frecuentes";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, faqs));
    }

    /* Lo que hace cuando recibe el metodo POST */
    @PostMapping
    public ResponseEntity<BaseResponse<FaqView>> create(@RequestBody FaqCreateRequest request) {

        final FaqView faq = this.faqService.create(request.question(), request.answer());

        final String message = "Pregunta frecuente creada";
        return ResponseEntity.status(201).body(new BaseResponse<>(message, faq));
    }

    @PutMapping
    public ResponseEntity<BaseResponse<FaqView>> update(@RequestBody FaqUpdateRequest request) {
        final FaqView faq = this.faqService.update(
                request.id(),
                request.question(),
                request.answer(),
                request.isVisible()
        );

        final String message = "Pregunta frecuente actualizada";
        return ResponseEntity.status(200).body(new BaseResponse<>(message, faq));
    }
}
