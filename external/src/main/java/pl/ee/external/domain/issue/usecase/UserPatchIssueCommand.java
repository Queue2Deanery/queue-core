package pl.ee.external.domain.issue.usecase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.external.domain.issue.dto.UserPatchIssueRequest;
import pl.ee.external.domain.issue.dto.UserPatchIssueResponse;
import pl.ee.external.infrastructure.repository.ActiveIssueViewRepository;
import pl.ee.external.infrastructure.repository.IssueCategoryRepository;
import pl.ee.external.infrastructure.repository.IssueRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserPatchIssueCommand {

  private ActiveIssueViewRepository activeIssueViewRepository;

  private IssueRepository issueRepository;

  private IssueCategoryRepository issueCategoryRepository;

  public UserPatchIssueResponse logic(UserPatchIssueRequest request, String userIndex) {
    if (request.getId() == null) {
      throw new EntityNotFoundException("id");
    }

    var issueEntity = issueRepository.findById(request.getId())
      .orElseThrow(() -> new EntityNotFoundException("id = " + request.getId()));

    activeIssueViewRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFoundException("nie aktualne zgłoszenie"));

    if (request.getIssueCategoryId() != null) {
      var categoryEntity = issueCategoryRepository.findById(request.getIssueCategoryId())
        .orElseThrow(() -> new EntityNotFoundException("nie ma takiej kategorii zgłoszenia"));
      issueEntity.setIssueCategoryEntity(categoryEntity);
    }
    if (request.getStudentComment() != null) {
      issueEntity.setStudentComment(request.getStudentComment());
    }

    var updatedIssue = issueRepository.save(issueEntity);

    return UserPatchIssueResponse.builder().issueId(updatedIssue.getId()).build();
  }
}
