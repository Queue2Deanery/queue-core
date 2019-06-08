package pl.ee.internal.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ee.internal.domain.issue.dto.*;
import pl.ee.internal.domain.issue.usecase.*;

import static org.springframework.http.ResponseEntity.ok;
import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RequestMapping(path = "/issue")
public class IssueController {

  private IssueDetailsQuery issueDetailsQuery;
  private IssuesQuery issuesQuery;
  private ActiveIssuesQuery activeIssuesQuery;
  private IssueProcessingStartCommand issueProcessingStartCommand;
  private IssueProcessingCompleteCommand issueProcessingCompleteCommand;
  private IssueRemoveCommand issueRemoveCommand;


  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping("/{issueId}")
  public ResponseEntity<IssueRemoveResponse> deleteIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long issueId) {
    return ok(issueRemoveCommand.logic(issueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/{queueId}")
  public ResponseEntity<IssuesResponse> getIssues(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId) {
    return ok(issuesQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/active/{queueId}")
  public ResponseEntity<IssuesResponse> getActiveIssues(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId) {
    return ok(activeIssuesQuery.logic(queueId));
  }


  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/details/{issueId}")
  public ResponseEntity<IssueDetailsResponse> getIssueDetails(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long issueId) {
    return ok(issueDetailsQuery.logic(issueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping("/start/{issueId}")
  public ResponseEntity<IssueProcessingStartResponse> startIssueProcessing(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long issueId) {
    return ok(issueProcessingStartCommand.logic(issueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping("/complete/{issueId}")
  public ResponseEntity<IssueProcessingCompleteResponse> completeIssueProcessing(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long issueId) {
    return ok(issueProcessingCompleteCommand.logic(issueId));
  }

}
