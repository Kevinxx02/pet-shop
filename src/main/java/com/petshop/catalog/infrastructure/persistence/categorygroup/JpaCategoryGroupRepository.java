package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.domain.categorygroup.CategoryGroup;
import com.petshop.catalog.domain.categorygroup.CategoryGroupRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCategoryGroupRepository implements CategoryGroupRepository {

    private final SpringDataCategoryGroupRepository jpaRepository;

    public JpaCategoryGroupRepository(SpringDataCategoryGroupRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void save(CategoryGroup categoryGroup) {
        jpaRepository.save(CategoryGroupMapper.toEntity(categoryGroup));
    }

    @Override
    public Optional<CategoryGroup> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(CategoryGroupMapper::toDomain);
    }

    @Override
    public boolean existsByGroupIdAndCategoryIdAndIdNot(UUID groupId, UUID categoryId, UUID id) {
        return this.jpaRepository.existsByGroupIdAndCategoryIdAndIdNot(groupId, categoryId, id);
    }
    @Override
    public boolean existsByGroupIdAndCategoryId(UUID groupId, UUID categoryId) {
        return this.jpaRepository.existsByGroupIdAndCategoryId(groupId, categoryId);
    }
}
