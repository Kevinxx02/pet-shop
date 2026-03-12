package com.petshop.catalog.application.faq;

import com.petshop.catalog.domain.faq.Faq;
import com.petshop.catalog.domain.faq.FaqRepository;
import com.petshop.catalog.infrastructure.persistence.faq.FaqJpaEntity;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FaqService {
    /* Definir el repository del cual obtendra la informacion */
    final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    /* Definir metodos que se utilizan desde el contralador */

    /* Obtener todos */
    public List<Faq> findAll() {
        return this.faqRepository.findAll();
    }

    /* Crear una nueva vinculacion entre grupos de categorias */
    public UUID create(String question, String answer) {
        /* Crea la entidad en el dominio */
        final Faq dominio = Faq.create(question, answer);

        /* Usa la entidad en el dominio para crear la entidad Jpa */
        final FaqJpaEntity entidad = faqRepository.toEntity(dominio);

        /* Guarda la entidad Jpa */
        faqRepository.save(entidad);

        /* Devuelve el ID */
        return dominio.getId();
    }

    /* Eliminar una categoria */
    public void delete(UUID id) {
        this.faqRepository.deleteById(id);
    }

    public UUID update(UUID id, String question, String answer) {
        FaqJpaEntity entity = faqRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        final Faq dominio = Faq.rehydrate(id, question, answer);
        entity.setQuestion(dominio.getQuestion());
        entity.setAnswer(dominio.getAnswer());

        return  dominio.getId();
    }
}
