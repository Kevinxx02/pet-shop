package com.petshop.catalog.infrastructure.persistence.faq;

import com.petshop.catalog.domain.faq.Faq;
import com.petshop.catalog.domain.faq.FaqRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaFaqRepository implements FaqRepository {

    private final SpringDataFaqRepository jpaRepository;

    public JpaFaqRepository(SpringDataFaqRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Faq faq) {
        this.jpaRepository.save(FaqMapper.toEntity(faq));
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public Optional<Faq> findById(UUID id) {
        return this.jpaRepository.findById(id).map(FaqMapper::toDomain);
    }
}
