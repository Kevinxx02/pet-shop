package com.petshop.catalog.infrastructure.persistence.faq;

import com.petshop.catalog.domain.faq.Faq;

public class FaqMapper {
    public static FaqJpaEntity toEntity(Faq faq) {
        return new FaqJpaEntity(faq.getId(), faq.getQuestion(),
                faq.getAnswer(), faq.getVisible()
        );
    }
}
