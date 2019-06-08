package pl.ee.internal.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ee.internal.domain.queue.dto.*;
import pl.ee.internal.domain.queue.usecase.QueueQuery;
import pl.ee.internal.domain.queue.usecase.QueuesQuery;

import static org.springframework.http.ResponseEntity.ok;
import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@AllArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RequestMapping(path = "/queue")
public class QueueController {

  private QueueQuery queueQuery;
  private QueuesQuery queuesQuery;

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping
  public ResponseEntity<QueuesResponse> getQueues() {
    return ok(queuesQuery.logic());
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/queueId")
  public ResponseEntity<QueueResponse> getQueue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId) {
    return ok(queueQuery.logic(queueId));
  }
}
