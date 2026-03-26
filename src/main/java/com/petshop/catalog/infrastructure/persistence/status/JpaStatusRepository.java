package com.petshop.catalog.infrastructure.persistence.status;

import com.petshop.catalog.domain.status.Status;
import com.petshop.catalog.domain.status.StatusRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaStatusRepository implements StatusRepository {

    private final SpringDataStatusRepository jpaRepository;

    public JpaStatusRepository(SpringDataStatusRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Status status) {
        jpaRepository.save(StatusMapper.toEntity(status));
    }

    @Override
    public void deleteById(UUID id) {
        this.jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }
}
