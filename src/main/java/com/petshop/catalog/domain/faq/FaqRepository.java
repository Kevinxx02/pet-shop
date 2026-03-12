package com.petshop.catalog.domain.faq;

import com.petshop.catalog.infrastructure.persistence.faq.FaqJpaEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FaqRepository {
    void save(FaqJpaEntity faq);

    FaqJpaEntity toEntity(Faq faq);
    Faq toDomain(FaqJpaEntity faq);
    List<Faq> findAll();
    void deleteById(UUID id);

    Optional<FaqJpaEntity> findById(UUID id);
}
