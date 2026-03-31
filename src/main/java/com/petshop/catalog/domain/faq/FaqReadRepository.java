package com.petshop.catalog.domain.faq;

import com.petshop.catalog.application.faq.FaqView;

import java.util.List;

public interface FaqReadRepository {
    List<FaqView> findAll();
}
