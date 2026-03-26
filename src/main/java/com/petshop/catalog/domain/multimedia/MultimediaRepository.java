package com.petshop.catalog.domain.multimedia;

import java.util.List;
import java.util.UUID;

public interface MultimediaRepository {
    void save(Multimedia multimedia);

    List<Multimedia> findAll();
    void deleteById(UUID id);
}
