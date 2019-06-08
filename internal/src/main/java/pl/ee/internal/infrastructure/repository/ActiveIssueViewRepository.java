package pl.ee.internal.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ee.common.model.ActiveIssueViewEntity;

import java.util.List;

public interface ActiveIssueViewRepository extends JpaRepository<ActiveIssueViewEntity, Long> {
  List<ActiveIssueViewEntity> findAllByQueueId(Long queueId);
}
