package com.petshop.catalog.infrastructure.persistence.multimedia;

import com.petshop.catalog.domain.multimedia.Multimedia;
import com.petshop.catalog.domain.multimedia.MultimediaRepository;
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
    public void save(Multimedia multimedia) {
        jpaRepository.save(MultimediaMapper.toEntity(multimedia));
    }

    @Override
    public List<Multimedia> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(MultimediaMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}
