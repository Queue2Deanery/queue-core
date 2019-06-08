package pl.ee.internal.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDetailsResponse {
  private Long id;
  private Long queueId;
  private LocalDateTime createdAt;
  private LocalDateTime startedAt;
  private LocalDateTime completedAt;
  private String studentIndex;
  private String issueCategoryName;
  private String studentComment;
  private Long estimatedTimeInSec;
}
