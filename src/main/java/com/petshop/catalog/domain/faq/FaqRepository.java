package com.petshop.catalog.domain.faq;

import java.util.Optional;
import java.util.UUID;

public interface FaqRepository {
    void save(Faq faq);

    boolean existsById(UUID id);

    Optional<Faq> findById(UUID id);
}
