package pl.ee.external.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ee.external.domain.issue.dto.*;
import pl.ee.external.domain.issue.usecase.*;

import java.util.List;

@RestController
@RequestMapping(path = "/issue")
public class IssueController {

  private ListBasicIssueQuery listBasicIssueQuery;
  private UserDeleteIssueCommand userDeleteIssueCommand;
  private UserIssueQuery userIssueQuery;
  private UserPatchIssueCommand userPatchIssueCommand;
  private UserSubmitIssueCommand userSubmitIssueCommand;

  @Autowired
  public IssueController(ListBasicIssueQuery listBasicIssueQuery, UserDeleteIssueCommand userDeleteIssueCommand, UserIssueQuery userIssueQuery, UserPatchIssueCommand userPatchIssueCommand, UserSubmitIssueCommand userSubmitIssueCommand) {
    this.listBasicIssueQuery = listBasicIssueQuery;
    this.userDeleteIssueCommand = userDeleteIssueCommand;
    this.userIssueQuery = userIssueQuery;
    this.userPatchIssueCommand = userPatchIssueCommand;
    this.userSubmitIssueCommand = userSubmitIssueCommand;
  }

  @GetMapping(path = "/{queueId}")
  public ResponseEntity<List<BasicIssueResponse>> getActiveIssueListByQueueId(@PathVariable Long queueId) {
    return ResponseEntity.ok(listBasicIssueQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('STUDENT')")
  @GetMapping(path = "/detail/{queueId}")
  public ResponseEntity<UserIssueResponse> getUserIssueById(@PathVariable Long queueId) {
    return ResponseEntity.ok(userIssueQuery.logic(queueId, null));
  }

  @PreAuthorize("hasRole('STUDENT')")
  @PostMapping // user can post only one issue to queue
  public ResponseEntity<UserSubmitIssueResponse> submitIssue(@RequestBody UserSubmitIssueRequest requestBody) {
    return ResponseEntity.ok(userSubmitIssueCommand.logic(requestBody));
  }

  @PreAuthorize("hasRole('STUDENT')")
  @DeleteMapping(path = "/{queueId}")
  public ResponseEntity<UserDeleteIssueResponse> deleteIssue(@PathVariable Long queueId) {
    return ResponseEntity.ok(userDeleteIssueCommand.logic(queueId, null));
  }

  @PreAuthorize("hasRole('STUDENT')")
  @PatchMapping
  public ResponseEntity<UserPatchIssueResponse> patchIssue(@RequestBody UserPatchIssueRequest requestBody) {
    return ResponseEntity.ok(userPatchIssueCommand.logic(requestBody));
  }
}
