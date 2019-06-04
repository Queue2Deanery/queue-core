package pl.ee.external.domain.issue.usecase;

import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.EntityNotFoundException;
import pl.ee.external.domain.issue.dto.UserDeleteIssueResponse;
import pl.ee.external.infrastructure.repository.IssueRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@AllArgsConstructor
@Service
public class UserDeleteIssueCommand {

  private IssueRepository issueRepository;

  // nor sure if it will work xD
  public UserDeleteIssueResponse logic(Long queueId, String userIndex) {
    var todayMidnight = LocalDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.MIDNIGHT);
    Try.run(() -> issueRepository
      .deleteByStartedAtIsNullAndQueueIdAndStudentIndexAndCreatedAtIsAfter(queueId, userIndex, todayMidnight)
    ).getOrElseThrow(throwable -> new EntityNotFoundException("queueId: " + queueId + "; userIndex: " + userIndex));

    return UserDeleteIssueResponse.builder().queueId(queueId).build();
  }
}
