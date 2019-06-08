package pl.ee.internal.domain.issue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssueDetailsResponse;

@Service
public class IssueDetailsQuery {
  public IssueDetailsResponse logic(@NonNull Long issueId) {
    return null;
  }
}
