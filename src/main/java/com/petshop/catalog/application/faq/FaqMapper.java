package com.petshop.catalog.application.faq;

import com.petshop.catalog.domain.faq.Faq;

public class FaqMapper {
    public static FaqView toView(Faq faq) {
        return new FaqView(faq.getId(), faq.getQuestion(), faq.getAnswer());
    }
}
