package pl.ee.external.domain.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QueueResponse {
  private Long id;
  private String name;
  private String shortName;
  private LocalTime registrationStart;
  private LocalTime registrationEnd;
  private List<EstimationResponse> issueProcessingEstimations;

  @AllArgsConstructor
  @NoArgsConstructor
  @Builder
  @Data
  public static class EstimationResponse {
    private Long issueCategoryId;
    private String issueCategoryName;
    private Long estimatedTimeInSec;
  }
}
