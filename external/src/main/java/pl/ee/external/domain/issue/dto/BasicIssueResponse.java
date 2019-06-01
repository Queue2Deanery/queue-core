package pl.ee.external.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicIssueResponse {

  private Long queueId;
  private String queueName;
  private String queueShortName;

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ActiveIssue {
    private Long id;
    private LocalDateTime createdAt;
    private String studentIndex;
  }

}
