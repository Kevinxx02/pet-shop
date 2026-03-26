package com.petshop.catalog.application.categorygroup;

import com.petshop.catalog.domain.category.CategoryRepository;
import com.petshop.catalog.domain.categorygroup.CategoryGroup;
import com.petshop.catalog.domain.categorygroup.CategoryGroupReadRepository;
import com.petshop.catalog.domain.categorygroup.CategoryGroupRepository;
import com.petshop.catalog.domain.group.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryGroupService {
    final CategoryGroupRepository categoryGroupRepository;
    final CategoryGroupReadRepository categoryGroupReadRepository;
    final CategoryRepository categoryRepository;
    final GroupRepository groupRepository;

    public CategoryGroupService(
            CategoryGroupRepository categoryGroupRepository,
            CategoryGroupReadRepository categoryGroupReadRepository,
            CategoryRepository categoryRepository,
            GroupRepository groupRepository
    ) {
        this.categoryGroupRepository = categoryGroupRepository;
        this.categoryGroupReadRepository = categoryGroupReadRepository;
        this.categoryRepository = categoryRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryGroupView> findAll() {
        return this.categoryGroupReadRepository.findAll();
    }

    @Transactional
    public CategoryGroupView create(UUID groupId, UUID categoryId) {
        this.validate(null, groupId, categoryId);

        final CategoryGroup categoryGroup = CategoryGroup.create(groupId, categoryId);

        categoryGroupRepository.save(categoryGroup);

        return CategoryGroupMapper.toView(categoryGroup);
    }

    @Transactional
    public CategoryGroupView update(
            UUID id,
            UUID groupId,
            UUID categoryId,
            boolean isActive
    ) {
        CategoryGroup categoryGroup = this.categoryGroupRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relación no encontrada"));

        this.validate(id, groupId, categoryId);

        categoryGroup.update(groupId, categoryId, isActive);

        this.categoryGroupRepository.save(categoryGroup);

        return CategoryGroupMapper.toView(categoryGroup);
    }

    private void validate(UUID id, UUID groupId, UUID categoryId) {
        if (!this.categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("La categoria no existe");
        }

        if (!this.groupRepository.existsById(groupId)) {
            throw new IllegalArgumentException("El grupo no existe");
        }

        if (id == null) {
            if (categoryGroupRepository.existsByGroupIdAndCategoryId(groupId, categoryId)) {
                throw new IllegalArgumentException("Ya existe esta relación");
            }
        } else {
            if (categoryGroupRepository.existsByGroupIdAndCategoryIdAndIdNot(groupId, categoryId, id)) {
                throw new IllegalArgumentException("Ya existe esta relación");
            }
        }
    }
}