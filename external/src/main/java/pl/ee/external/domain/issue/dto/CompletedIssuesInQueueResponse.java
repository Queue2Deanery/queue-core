package pl.ee.external.domain.issue.dto;

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
public class CompletedIssuesInQueueResponse {

  private Long queueId;
  private List<CompletedIssue> completedIssues;

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CompletedIssue {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private String studentIndex;
  }

}
