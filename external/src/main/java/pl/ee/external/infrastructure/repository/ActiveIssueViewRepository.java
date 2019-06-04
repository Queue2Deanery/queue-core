package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ee.common.model.ActiveIssueViewEntity;

import java.util.List;
import java.util.Optional;

public interface ActiveIssueViewRepository extends JpaRepository<ActiveIssueViewEntity, Long> {
  List<ActiveIssueViewEntity> findAllByQueueId(Long queueId);

  Optional<ActiveIssueViewEntity> findByQueueIdAndStudentIndex(Long queueId, String studentIndex);
}
