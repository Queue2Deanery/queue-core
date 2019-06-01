package pl.ee.external.domain.issue.usecase;

import org.springframework.stereotype.Service;
import pl.ee.external.domain.issue.dto.UserSubmitIssueRequest;
import pl.ee.external.domain.issue.dto.UserSubmitIssueResponse;

@Service
public class UserSubmitIssueCommand {
  public UserSubmitIssueResponse logic(UserSubmitIssueRequest request, String userIndex) {
    return null;
  }
}
