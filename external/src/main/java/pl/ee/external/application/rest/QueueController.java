package pl.ee.external.application.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pl.ee.external.domain.queue.dto.QueueResponse;
import pl.ee.external.domain.queue.dto.QueuesResponse;
import pl.ee.external.domain.queue.usecase.QueueQuery;
import pl.ee.external.domain.queue.usecase.QueuesQuery;
import pl.ee.external.domain.queue.usecase.UserQueuesQuery;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/queue")
public class QueueController {

  private QueueQuery queueQuery;

  private QueuesQuery queuesQuery;

  private UserQueuesQuery userQueuesQuery;

  @GetMapping
  public ResponseEntity<QueuesResponse> getQueueList() {
    return ok(queuesQuery.logic());
  }

  @GetMapping(path = "/{queueId}")
  public ResponseEntity<QueueResponse> getQueueById(@PathVariable Long queueId) {
    return ok(queueQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @GetMapping(path = "/user")
  public ResponseEntity<QueuesResponse> getUserQueues(@ApiIgnore Authentication authentication) {
    return ok(userQueuesQuery.logic(mapRoles(authentication)));
  }

  private List<String> mapRoles(Authentication authentication) {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
  }
}
