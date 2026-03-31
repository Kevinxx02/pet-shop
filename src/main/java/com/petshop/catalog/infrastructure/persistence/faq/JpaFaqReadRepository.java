package com.petshop.catalog.infrastructure.persistence.faq;

import com.petshop.catalog.application.faq.FaqView;
import com.petshop.catalog.domain.faq.FaqReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaFaqReadRepository implements FaqReadRepository {

    private final SpringDataFaqReadRepository jpaRepository;

    public JpaFaqReadRepository(SpringDataFaqReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<FaqView> findAll() {
        return this.jpaRepository.viewAll();
    }
}
