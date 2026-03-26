package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.application.multimedia.MultimediaView;
import com.petshop.catalog.domain.multimedia.MultimediaReadRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaMultimediaReadRepository implements MultimediaReadRepository {

    private final SpringDataMultimediaReadRepository jpaRepository;

    public JpaMultimediaReadRepository(SpringDataMultimediaReadRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<MultimediaView> findAllView() {
        return this.jpaRepository.findAllView();
    }
}
