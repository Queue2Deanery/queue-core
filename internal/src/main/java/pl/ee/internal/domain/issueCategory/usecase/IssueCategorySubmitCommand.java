package pl.ee.internal.domain.issueCategory.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issueCategory.dto.IssueCategorySubmitRequest;
import pl.ee.internal.domain.issueCategory.dto.IssueCategorySubmitResponse;

@Service
public class IssueCategorySubmitCommand {
  public IssueCategorySubmitResponse logic(@NonNull IssueCategorySubmitRequest request) {
    return null;
  }
}
