package pl.ee.external.domain.issue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.external.domain.issue.dto.UserActiveIssueResponse;
import pl.ee.external.infrastructure.repository.ActiveIssueViewRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserIssueQuery {

  private ActiveIssueViewRepository activeIssueViewRepository;

  public UserActiveIssueResponse logic(Long queueId, String studentIndex) {

    var issueEntity = activeIssueViewRepository.findByQueueIdAndStudentIndex(queueId, studentIndex)
      .orElseThrow(() -> new EntityNotFoundException("queueId = " + queueId + ", studentIndex = " + studentIndex));

    return UserActiveIssueResponse.builder()
      .id(issueEntity.getId())
      .createdAt(issueEntity.getCreatedAt())
      .estimatedTimeInSec(issueEntity.getEstimatedTimeInSec())
      .queueId(issueEntity.getQueueId())
      .startedAt(issueEntity.getStartedAt())
      .studentComment(issueEntity.getStudentComment())
      .studentIndex(issueEntity.getStudentIndex())
      .build();
  }
}
