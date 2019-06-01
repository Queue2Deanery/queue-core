package pl.ee.external.domain.issue.usecase;

import org.springframework.stereotype.Service;
import pl.ee.external.domain.issue.dto.UserPatchIssueRequest;
import pl.ee.external.domain.issue.dto.UserPatchIssueResponse;

@Service
public class UserPatchIssueCommand {
  public UserPatchIssueResponse logic(UserPatchIssueRequest request, String userIndex) {
    return null;
  }
}
