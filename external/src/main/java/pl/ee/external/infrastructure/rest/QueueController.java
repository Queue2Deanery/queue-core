package pl.ee.external.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ee.external.domain.queue.dto.QueueResponse;
import pl.ee.external.infrastructure.exception.EntityNotFoundException;

import java.util.List;

@RestController
@RequestMapping(path = "/queue")
public class QueueController {

  @GetMapping
  public ResponseEntity<List<QueueResponse>> getQueueList() {
    return ResponseEntity.ok(List.of(QueueResponse.builder().build()));
  }

  @GetMapping(path = "/{queueId}")
  public ResponseEntity<QueueResponse> getQueueById(@PathVariable Long queueId) {
    return ResponseEntity.ok(QueueResponse.builder().build());
  }

}
