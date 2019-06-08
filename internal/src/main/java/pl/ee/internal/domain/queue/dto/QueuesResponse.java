package pl.ee.internal.domain.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueuesResponse {
  private List<ShortQueueResponse> queues;

  @Builder
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ShortQueueResponse {
    private Long id;
    private String name;
    private String shortName;
    private LocalTime registrationStart;
    private LocalTime registrationEnd;
  }
}
