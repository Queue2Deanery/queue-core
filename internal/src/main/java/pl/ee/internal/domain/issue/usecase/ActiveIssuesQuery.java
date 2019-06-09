package pl.ee.internal.domain.issue.usecase;

import io.github.resilience4j.core.lang.NonNull;
import io.vavr.Function1;
import org.springframework.stereotype.Service;
import pl.ee.common.model.ActiveIssueViewEntity;
import pl.ee.internal.domain.issue.dto.IssuesResponse;
import pl.ee.internal.domain.issue.dto.IssuesResponse.Issue;
import pl.ee.internal.infrastructure.repository.ActiveIssueViewRepository;
import pl.ee.internal.infrastructure.repository.QueueRepository;

@Service
public class ActiveIssuesQuery {

  private final Function1<ActiveIssueViewEntity, Issue> mapIssue = (issue) ->
    Issue.builder()
      .id(issue.getId())
      .studentIndex(issue.getStudentIndex())
      .createdAt(issue.getCreatedAt())
      .startedAt(issue.getStartedAt())
      .issueCategoryName(issue.getIssueCategoryName())
      .estimatedTimeInSec(issue.getEstimatedTimeInSec())
      .build();
  private ActiveIssueViewRepository activeIssueViewRepository;
  private QueueRepository queueRepository;

  public IssuesResponse logic(@NonNull Long queueId) {
    return null;
  }
}
