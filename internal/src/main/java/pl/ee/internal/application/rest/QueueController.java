package pl.ee.internal.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pl.ee.internal.domain.queue.dto.*;
import pl.ee.internal.domain.queue.usecase.QueueQuery;
import pl.ee.internal.domain.queue.usecase.QueueRemoveCommand;
import pl.ee.internal.domain.queue.usecase.QueueSubmitCommand;
import pl.ee.internal.domain.queue.usecase.QueuesQuery;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@AllArgsConstructor
@RestController
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RequestMapping(path = "/queue")
public class QueueController {

  private QueueQuery queueQuery;
  private QueueRemoveCommand queueRemoveCommand;
  private QueuesQuery queuesQuery;
  private QueueSubmitCommand queueSubmitCommand;

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping
  public ResponseEntity<QueuesResponse> getQueues() {
    return ok(queuesQuery.logic());
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/queueId")
  public ResponseEntity<QueueResponse> getQueue(@PathVariable Long queueId) {
    return ok(queueQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @DeleteMapping("/{queueId}")
  public ResponseEntity<QueueRemoveResponse> removeQueue(@PathVariable Long queueId) {
    return ok(queueRemoveCommand.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping
  public ResponseEntity<QueueSubmitResponse> submitQueue(@RequestBody QueueSubmitRequest request) {
    return ok(queueSubmitCommand.logic(request));
  }

  private List<String> mapRoles(Authentication authentication) {
    return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
  }
}
