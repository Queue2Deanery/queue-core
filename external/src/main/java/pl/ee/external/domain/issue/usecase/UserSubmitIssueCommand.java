package pl.ee.external.domain.issue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.common.model.IssueEntity;
import pl.ee.external.domain.issue.dto.UserSubmitIssueRequest;
import pl.ee.external.domain.issue.dto.UserSubmitIssueResponse;
import pl.ee.external.infrastructure.repository.IssueCategoryRepository;
import pl.ee.external.infrastructure.repository.IssueRepository;
import pl.ee.external.infrastructure.repository.QueueRepository;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
public class UserSubmitIssueCommand {

  private IssueRepository issueRepository;
  private IssueCategoryRepository issueCategoryRepository;
  private QueueRepository queueRepository;

  public UserSubmitIssueResponse logic(@Valid UserSubmitIssueRequest request, String userIndex) {


    var category = issueCategoryRepository.findById(request.getIssueCategoryId())
      .orElseThrow(() -> new EntityNotFoundException("id kategorii zgłoszenia: " + request.getIssueCategoryId()));

    var queue = queueRepository.findById(request.getQueueId()).orElseThrow(() -> new EntityNotFoundException("id kolejki: " + request.getIssueCategoryId()));

    var newIssue = IssueEntity.builder().issueCategoryEntity(category).queueEntity(queue).studentComment(request.getStudentComment()).build();

    var res = issueRepository.save(newIssue);

    return UserSubmitIssueResponse.builder().issueId(res.getId()).build();
  }
}
