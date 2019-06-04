package pl.ee.external.application.rest;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import pl.ee.external.domain.issue.dto.*;
import pl.ee.external.domain.issue.usecase.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@Slf4j
@RestController
@RequiredArgsConstructor
@AllArgsConstructor
@RequestMapping(path = "/issue")
public class IssueController {

  private ActiveIssuesInQueueQuery activeIssuesInQueueQuery;
  private UserIssueQuery userIssueQuery;
  private UserDeleteIssueCommand userDeleteIssueCommand;
  private UserPatchIssueCommand userPatchIssueCommand;
  private UserSubmitIssueCommand userSubmitIssueCommand;

  @GetMapping(path = "/{queueId}")
  public ResponseEntity<ActiveIssuesInQueueResponse> getActiveIssueListByQueueId(@PathVariable Long queueId) {
    return ok(activeIssuesInQueueQuery.logic(queueId));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT') and @accessQueue.hasRole(#queueId)")
  @GetMapping(path = "/detail/{queueId}")
  public ResponseEntity<UserActiveIssueResponse> getUserIssueById(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId, @ApiIgnore Authentication authentication) {
    return ok(userIssueQuery.logic(queueId, getStudentIndex(authentication)));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT') and @accessQueue.hasRole(#requestBody.getQueueId())")
  @PostMapping // user can post only one issue to queue
  public ResponseEntity<UserSubmitIssueResponse> submitIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @RequestBody UserSubmitIssueRequest requestBody, @ApiIgnore Authentication authentication) {
    return ok(userSubmitIssueCommand.logic(requestBody, getStudentIndex(authentication)));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT') and @accessQueue.hasRole(#queueId)")
  @DeleteMapping(path = "/{queueId}")
  public ResponseEntity<UserDeleteIssueResponse> deleteIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long queueId, @ApiIgnore Authentication authentication) {
    return ok(userDeleteIssueCommand.logic(queueId, getStudentIndex(authentication)));
  }

  @PreAuthorize("hasRole('ROLE_STUDENT') and @accessQueue.hasIssue(#requestBody.getId())")
  @PatchMapping
  public ResponseEntity<UserPatchIssueResponse> patchIssue(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @RequestBody UserPatchIssueRequest requestBody, @ApiIgnore Authentication authentication) {
    return ok(userPatchIssueCommand.logic(requestBody, getStudentIndex(authentication)));
  }

  private String getStudentIndex(Authentication authentication) {
    return authentication.getPrincipal().toString();
  }
}
