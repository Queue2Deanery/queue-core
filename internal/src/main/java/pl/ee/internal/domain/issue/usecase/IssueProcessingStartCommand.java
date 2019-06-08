package pl.ee.internal.domain.issue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssueProcessingStartResponse;

@Service
public class IssueProcessingStartCommand {
  public IssueProcessingStartResponse logic(@NonNull Long issueId) {
    return null;
  }
}
