package com.petshop.catalog.domain.multimedia;

import com.petshop.catalog.application.multimedia.MultimediaView;

import java.util.List;

public interface MultimediaReadRepository {
    List<MultimediaView> viewAll();
}
