package pl.ee.external.domain.queue.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QueueRequest {
  private Long queueId;
}