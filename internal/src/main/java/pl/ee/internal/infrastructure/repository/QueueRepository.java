package pl.ee.internal.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.common.model.QueueEntity;

@Repository
public interface QueueRepository extends JpaRepository<QueueEntity, Long> {
}
