package com.petshop.catalog.infrastructure.persistence.group;

import com.petshop.catalog.domain.group.GroupRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class JpaGroupRepository implements GroupRepository {

    private final SpringDataGroupRepository jpaRepository;

    public JpaGroupRepository(SpringDataGroupRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public boolean existsById(UUID groupId) {
        return this.jpaRepository.existsById(groupId);
    }
}
