package com.petshop.catalog.infrastructure.persistence.filter;

import com.petshop.catalog.domain.filter.Filter;
import com.petshop.catalog.domain.filter.FilterRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaFilterRepository implements FilterRepository {

    private final SpringDataFilterRepository jpaRepository;

    public JpaFilterRepository(SpringDataFilterRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(Filter filter) {
        FilterJpaEntity entity = FilterMapper.toEntity(filter);
        this.jpaRepository.save(entity);
    }

    @Override
    public Optional<Filter> findById(UUID id) {
        return this.jpaRepository.findById(id).map(FilterMapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return this.jpaRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        this.jpaRepository.deleteById(id);
    }
}
