package pl.ee.internal.domain.issue.usecase;

import io.github.resilience4j.core.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issue.dto.IssuesResponse;

@Service
public class ActiveIssuesQuery {
  public IssuesResponse logic(@NonNull Long queueId) {
    return null;
  }
}
