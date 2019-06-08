package pl.ee.internal.domain.issue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssueRemoveResponse;

@Service
public class IssueRemoveCommand {
  public IssueRemoveResponse logic(@NonNull Long issueId) {
    return null;
  }
}
