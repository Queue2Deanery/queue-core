package pl.ee.internal.domain.issue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssueProcessingCompleteResponse;

@Service
public class IssueProcessingCompleteCommand {
  public IssueProcessingCompleteResponse logic(@NonNull Long issueId) {
    return null;
  }
}
