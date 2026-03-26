package com.petshop.catalog.infrastructure.persistence.categorygroup;

import com.petshop.catalog.application.categorygroup.CategoryGroupView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataCategoryGroupReadRepository extends JpaRepository<CategoryGroupJpaEntity, UUID> {
    @Query("""
        SELECT new com.petshop.catalog.application.categorygroup.CategoryGroupView(
            cg.id,
            cg.groupId,
            cg.categoryId
        )
        FROM CategoryGroupJpaEntity cg
""")
    List<CategoryGroupView> findActive();
}