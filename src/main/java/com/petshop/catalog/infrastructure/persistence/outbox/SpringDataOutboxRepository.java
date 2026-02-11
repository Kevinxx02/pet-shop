package com.petshop.catalog.infrastructure.persistence.outbox;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface SpringDataOutboxRepository extends JpaRepository<OutboxEventJpaEntity, UUID> {
    @Modifying
    @Query("""
        update OutboxEventJpaEntity e
        set e.status = 'PROCESSING',
            e.attempts = e.attempts + 1
        where e.id = :id
          and e.status in ('PENDING', 'FAILED')
    """)
    int markAsProcessingWithAtomicLocking(@Param("id") UUID id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
    select e from OutboxEventJpaEntity e
    where e.attempts < :max_attempts
        and e.status in :statuses
      and e.nextAttemptAt <= :now
""")
    List<OutboxEventJpaEntity> findByStatusInAndNextAttemptAtLessThanEqualAndAttemptsLessThan(List<OutboxStatus> statuses, Instant now, int max_attempts);
}