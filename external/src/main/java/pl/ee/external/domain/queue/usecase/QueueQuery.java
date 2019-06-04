package pl.ee.external.domain.queue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.BusinessException;
import pl.ee.external.domain.queue.dto.QueueResponse;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.external.infrastructure.repository.IssueProcessingEstimationViewRepository;
import pl.ee.external.infrastructure.repository.QueueRepository;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QueueQuery {

  private QueueRepository queueRepository;

  private IssueProcessingEstimationViewRepository processingEstimationViewRepository;


  @Autowired
  public QueueQuery(QueueRepository queueRepository) {
    this.queueRepository = queueRepository;
  }

  public QueueResponse logic(Long id) {
    if (id == null) {
      throw new BusinessException("podaj id");
    }
    var queueEntity = queueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("kolejka o id: " + id));

    var estimations = processingEstimationViewRepository.findAllByIssueProcessingEstimationPK_QueueId(id);

    return QueueResponse.builder()
      .id(queueEntity.getId())
      .registrationEnd(queueEntity.getRegistrationEnd())
      .registrationStart(queueEntity.getRegistrationStart())
      .name(queueEntity.getName())
      .shortName(queueEntity.getShortName())
      .issueProcessingEstimations(estimations.stream().map(estimation ->
        QueueResponse.EstimationResponse.builder()
          .issueCategoryId(estimation.getIssueProcessingEstimationPK().getIssueCategoryId())
          .issueCategoryName(estimation.getIssueCategoryName())
          .estimatedTimeInSec(estimation.getEstimatedTimeInSec())
          .build()).collect(Collectors.toList()))
      .build();
  }
}
