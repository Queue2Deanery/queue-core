package pl.ee.external.domain.issue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.external.domain.issue.dto.BasicIssueResponse;
import pl.ee.external.infrastructure.repository.IssueCategoryRepository;
import pl.ee.external.infrastructure.repository.IssueRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ListBasicIssueQuery {

  private IssueRepository issueRepository;

  private IssueCategoryRepository issueCategoryRepository;

  public List<BasicIssueResponse> logic(Long queueId) {
    var activeIssues = issueRepository.findByQueueIdAndCompletedAt(queueId, null);

    return null;
  }
}
