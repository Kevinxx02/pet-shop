package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.domain.multimedia.MultimediaRepository;
import com.petshop.catalog.domain.product.Product;
import com.petshop.catalog.infrastructure.persistence.product.ProductJpaEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class JpaMultimediaRepository implements MultimediaRepository {

    private final SpringDataMultimediaRepository jpaRepository;

    public JpaMultimediaRepository(SpringDataMultimediaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(MultimediaJpaEntity multimedia) {
        jpaRepository.save(multimedia);
    }

    @Override
    public MultimediaJpaEntity toEntity(Multimedia multimedia) {
        MultimediaJpaEntity entity = new MultimediaJpaEntity();
        entity.setId(multimedia.getId());
        entity.setOwnerId(multimedia.getOwnerId());
        entity.setFileName(multimedia.getFileName());
        entity.setIsPrimary(multimedia.getIsPrimary());

        return entity;
    }

    @Override
    public Multimedia toDomain(MultimediaJpaEntity entity) {
        return Multimedia.rehydrate(entity.getId(), entity.getOwnerId(), entity.getFileName(), entity.getIsPrimary());
    }

    @Override
    public List<Multimedia> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
