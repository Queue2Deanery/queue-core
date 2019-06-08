package pl.ee.internal.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuesResponse {
  private Long queueId;
  private List<Issue> issues;

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Issue {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private String studentIndex;
    private String issueCategoryName;
    private Long estimatedTimeInSec;
  }

}
