package pl.ee.internal.domain.issueCategory.usecase;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import pl.ee.internal.domain.issueCategory.dto.IssueCategoryRemoveResponse;

@Service
public class IssueCategoryRemoveCommand {
  public IssueCategoryRemoveResponse logic(@NonNull Long issueCategoryId) {
    return null;
  }
}
