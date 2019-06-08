package pl.ee.internal.domain.issue.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssuesResponse;

@Service
public class IssuesQuery {
  public IssuesResponse logic(@NonNull Long queueId) {
    return null;
  }
}
