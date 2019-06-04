package pl.ee.external.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActiveIssueResponse {
  private Long id;
  private Long queueId;
  private LocalDateTime createdAt;
  private LocalDateTime startedAt;
  private String studentIndex;
  private String studentComment;
  private Long estimatedTimeInSec;
}
