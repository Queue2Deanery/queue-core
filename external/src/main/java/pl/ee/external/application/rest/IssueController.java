package pl.ee.external.application.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.ee.external.domain.issue.dto.*;
import pl.ee.external.domain.issue.usecase.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@Slf4j
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

  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @GetMapping(path = "/detail/{queueId}")
  public ResponseEntity<UserIssueResponse> getUserIssueById(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId, @ApiIgnore Authentication authentication) {
    return ResponseEntity.ok(userIssueQuery.logic(queueId, authentication.getPrincipal().toString()));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @PostMapping // user can post only one issue to queue
  public ResponseEntity<UserSubmitIssueResponse> submitIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @RequestBody UserSubmitIssueRequest requestBody, @ApiIgnore Authentication authentication) {
    return ResponseEntity.ok(userSubmitIssueCommand.logic(requestBody, authentication.getPrincipal().toString()));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @DeleteMapping(path = "/{queueId}")
  public ResponseEntity<UserDeleteIssueResponse> deleteIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId, @ApiIgnore Authentication authentication) {
    return ResponseEntity.ok(userDeleteIssueCommand.logic(queueId, authentication.getPrincipal().toString()));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT')")
  @PatchMapping
  public ResponseEntity<UserPatchIssueResponse> patchIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @RequestBody UserPatchIssueRequest requestBody, @ApiIgnore Authentication authentication) {
    return ResponseEntity.ok(userPatchIssueCommand.logic(requestBody, authentication.getPrincipal().toString()));
  }
}