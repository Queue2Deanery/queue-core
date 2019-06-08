package pl.ee.internal.application.rest;

import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.ee.internal.domain.issue.dto.IssueDetailsResponse;
import pl.ee.internal.domain.issue.dto.IssueProcessingCompleteResponse;
import pl.ee.internal.domain.issue.dto.IssueProcessingStartResponse;
import pl.ee.internal.domain.issue.dto.IssuesResponse;
import pl.ee.internal.domain.issue.usecase.IssueDetailsQuery;
import pl.ee.internal.domain.issue.usecase.IssueProcessingCompleteCommand;
import pl.ee.internal.domain.issue.usecase.IssueProcessingStartCommand;
import pl.ee.internal.domain.issue.usecase.IssuesQuery;
import springfox.documentation.annotations.ApiIgnore;

import static org.springframework.http.ResponseEntity.ok;
import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RequestMapping(path = "/issue")
public class IssueController {

  private IssueDetailsQuery issueDetailsQuery;
  private IssueProcessingStartCommand issueProcessingStartCommand;
  private IssueProcessingCompleteCommand issueProcessingCompleteCommand;
  private IssuesQuery issuesQuery;


  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/{queueId}")
  public ResponseEntity<IssuesResponse> getIssues(@PathVariable Long queueId) {
    return ok(issuesQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @GetMapping("/details/{issueId}")
  public ResponseEntity<IssueDetailsResponse> getIssueDetails(@PathVariable Long issueId) {
    return ok(issueDetailsQuery.logic(issueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping("/start/{issueId}")
  public ResponseEntity<IssueProcessingStartResponse> startIssueProcessing(@PathVariable Long issueId) {
    return ok(issueProcessingStartCommand.logic(issueId));
  }

  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  @PostMapping("/complete/{issueId}")
  public ResponseEntity<IssueProcessingCompleteResponse> completeIssueProcessing(@PathVariable Long issueId) {
    return ok(issueProcessingCompleteCommand.logic(issueId));
  }


}
