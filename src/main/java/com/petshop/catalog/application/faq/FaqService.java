package com.petshop.catalog.application.faq;

import com.petshop.catalog.domain.faq.Faq;
import com.petshop.catalog.domain.faq.FaqReadRepository;
import com.petshop.catalog.domain.faq.FaqRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FaqService {
    /* Definir el repository del cual obtendra la informacion */
    final FaqRepository faqRepository;
    final FaqReadRepository faqReadRepository;

    public FaqService(FaqRepository faqRepository,
                      FaqReadRepository faqReadRepository
    ) {
        this.faqRepository = faqRepository;
        this.faqReadRepository = faqReadRepository;
    }

    /* Crear una nueva vinculacion entre grupos de categorias */
    public FaqView create(String question, String answer) {
        /* Crea la entidad en el dominio */
        final Faq faq = Faq.create(question, answer);

        /* Guarda la entidad Jpa */
        faqRepository.save(faq);

        /* Devuelve el ID */
        return FaqMapper.toView(faq);
    }

    public FaqView update(UUID id, String question, String answer, boolean isVisible) {
        if (!this.faqRepository.existsById(id)) {
            throw new RuntimeException("Pregunta no encontrada");
        }

        final Faq faq = Faq.rehydrate(id, question, answer, isVisible);

        this.faqRepository.save(faq);

        return FaqMapper.toView(faq);
    }

    public List<FaqView> viewAll() {
        return this.faqReadRepository.findAll();
    }
}
