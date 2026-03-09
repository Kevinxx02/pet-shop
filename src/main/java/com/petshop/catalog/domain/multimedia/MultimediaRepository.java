package com.petshop.catalog.domain.multimedia;

import com.petshop.catalog.infrastructure.persistence.multimedia.MultimediaJpaEntity;

import java.util.List;
import java.util.UUID;

public interface MultimediaRepository {
    void save(MultimediaJpaEntity multimedia);

    MultimediaJpaEntity toEntity(Multimedia multimedia);
    Multimedia toDomain(MultimediaJpaEntity multimedia);
    List<Multimedia> findAll();
    void deleteById(UUID id);
}
