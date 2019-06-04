package pl.ee.external.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiveIssuesInQueueResponse {

  private Long queueId;
  private List<ActiveIssue> activeIssues;

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ActiveIssue {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private String studentIndex;
    private String issueCategoryName;
    private Long estimatedTimeInSec;
  }

}
