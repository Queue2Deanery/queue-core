package pl.ee.external.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ee.common.model.IssueProcessingEstimationPK;
import pl.ee.common.model.IssueProcessingEstimationViewEntity;

import java.util.List;

public interface IssueProcessingEstimationViewRepository extends JpaRepository<IssueProcessingEstimationViewEntity, IssueProcessingEstimationPK> {
  List<IssueProcessingEstimationViewEntity> findAllByIssueProcessingEstimationPK_QueueId(Long queueId);
}
