package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;

public class MultimediaMapper {
    public static Multimedia toDomain(MultimediaJpaEntity entity) {
        return Multimedia.rehydrate(
                entity.getId(),
                entity.getOwnerId(),
                entity.getFileName(),
                entity.getIsPrimary()
        );
    }
    public static MultimediaJpaEntity toEntity(Multimedia multimedia) {
        return new MultimediaJpaEntity(
                multimedia.getId(),
                multimedia.getOwnerId(),
                multimedia.getFileName(),
                multimedia.getIsPrimary()
        );
    }
}
