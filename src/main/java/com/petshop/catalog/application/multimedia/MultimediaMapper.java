package com.petshop.catalog.application.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;

public class MultimediaMapper {
    public static MultimediaView toView(Multimedia multimedia) {
        return new MultimediaView(
                multimedia.getId(),
                multimedia.getFileName(),
                multimedia.getIsPrimary(),
                multimedia.getOwnerId()
        );
    }
}
