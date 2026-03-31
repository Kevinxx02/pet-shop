package com.petshop.catalog.infrastructure.persistence.faq;

import com.petshop.catalog.application.faq.FaqView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataFaqReadRepository extends JpaRepository<FaqJpaEntity, UUID> {
    @Query("""
    SELECT new com.petshop.catalog.application.faq.FaqView(
        id,
        question,
        answer
    )
    FROM FaqJpaEntity
    WHERE isVisible = true
""")
    List<FaqView> viewAll();
}
