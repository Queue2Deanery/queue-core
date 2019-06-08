package pl.ee.external.domain.issue.usecase;

import io.vavr.Function1;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.common.model.ActiveIssueViewEntity;
import pl.ee.external.domain.issue.dto.ActiveIssuesInQueueResponse;
import pl.ee.external.infrastructure.repository.ActiveIssueViewRepository;
import pl.ee.external.infrastructure.repository.QueueRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ActiveIssuesInQueueQuery {

  private ActiveIssueViewRepository activeIssueViewRepository;

  private QueueRepository queueRepository;

  private final Function1<ActiveIssueViewEntity, ActiveIssuesInQueueResponse.ActiveIssue> mapIssue = (issue) ->
    ActiveIssuesInQueueResponse.ActiveIssue.builder()
      .id(issue.getId())
      .studentIndex(issue.getStudentIndex())
      .createdAt(issue.getCreatedAt())
      .startedAt(issue.getStartedAt())
      .issueCategoryName(issue.getIssueCategoryName())
      .estimatedTimeInSec(issue.getEstimatedTimeInSec())
      .build();

  public ActiveIssuesInQueueResponse logic(Long queueId) {
    if (queueId == null) {
      throw new EntityNotFoundException("Brak id kolejki");
    }

    queueRepository.findById(queueId)
      .orElseThrow(() -> new EntityNotFoundException("Kolejka o id = " + queueId + " nie istnieje"));

    var activeIssuesEntities = activeIssueViewRepository.findAllByQueueId(queueId);

    var activeIssues = activeIssuesEntities.stream()
      .sorted(Comparator.comparing(ActiveIssueViewEntity::getCreatedAt))
      .map(mapIssue)
      .collect(Collectors.toList());

    return ActiveIssuesInQueueResponse.builder().queueId(queueId).activeIssues(activeIssues).build();
  }

}
