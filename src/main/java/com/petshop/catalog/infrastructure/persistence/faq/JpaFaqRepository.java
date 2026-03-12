package com.petshop.catalog.infrastructure.persistence.faq;

import com.petshop.catalog.domain.faq.Faq;
import com.petshop.catalog.domain.faq.FaqRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaFaqRepository implements FaqRepository {

    private final SpringDataFaqRepository jpaRepository;

    public JpaFaqRepository(SpringDataFaqRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(FaqJpaEntity entity) {
        jpaRepository.save(entity);
    }

    @Override
    public FaqJpaEntity toEntity(Faq faq) {
        FaqJpaEntity entity = new FaqJpaEntity();
        entity.setId(faq.getId());
        entity.setQuestion(faq.getQuestion());
        entity.setAnswer(faq.getAnswer());

        return entity;
    }

    @Override
    public Faq toDomain(FaqJpaEntity entity) {
        return Faq.rehydrate(entity.getId(), entity.getQuestion(), entity.getAnswer());
    }

    @Override
    public List<Faq> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<FaqJpaEntity> findById(UUID id) {
        return jpaRepository.findById(id);
    }
}
