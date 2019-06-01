package pl.ee.external.domain.issue.usecase;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.common.exception.dto.ApiError;
import pl.ee.external.domain.issue.dto.UserDeleteIssueResponse;
import pl.ee.external.infrastructure.repository.IssueRepository;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserDeleteIssueCommand {

  private IssueRepository issueRepository;

  public UserDeleteIssueResponse logic(Long queueId, String userIndex) {
    Try.run(() -> issueRepository.deleteAwaitingIssueByQueueIdAndUser(queueId, userIndex)).getOrElseThrow(throwable -> new EntityNotFoundException("queueId: " + queueId + "; userIndex: " + userIndex));
    return UserDeleteIssueResponse.builder()
      .message(List.of(
        Map.entry(ApiError.Language.en, "Issue awaiting processing was deleted successfully"),
        Map.entry(ApiError.Language.pl, "Zgłoszenie oczekujące na przyjęcie przez dziekanat zostało usunięte")
      )).build();
  }
}
