package pl.ee.internal.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.ee.internal.domain.issueCategory.dto.IssueCategoriesResponse;
import pl.ee.internal.domain.issueCategory.dto.IssueCategoryRemoveResponse;
import pl.ee.internal.domain.issueCategory.dto.IssueCategorySubmitRequest;
import pl.ee.internal.domain.issueCategory.dto.IssueCategorySubmitResponse;
import pl.ee.internal.domain.issueCategory.usecase.IssueCategoriesQuery;
import pl.ee.internal.domain.issueCategory.usecase.IssueCategoryRemoveCommand;
import pl.ee.internal.domain.issueCategory.usecase.IssueCategorySubmitCommand;

import static org.springframework.http.ResponseEntity.ok;
import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/issue-category")
public class IssueCategoryController {

  private IssueCategoriesQuery issueCategoriesQuery;
  private IssueCategoryRemoveCommand issueCategoryRemoveCommand;
  private IssueCategorySubmitCommand issueCategorySubmitCommand;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public ResponseEntity<IssueCategoriesResponse> getIssueCategories(@RequestHeader(value = TOKEN_HEADER_NAME) String token) {
    return ok(issueCategoriesQuery.logic());
  }

  @DeleteMapping("/{issueCategoryId}")
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public ResponseEntity<IssueCategoryRemoveResponse> removeIssueCategory(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @PathVariable Long issueCategoryId) {
    return ok(issueCategoryRemoveCommand.logic(issueCategoryId));
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
  public ResponseEntity<IssueCategorySubmitResponse> submitIssueCategory(@RequestHeader(value = TOKEN_HEADER_NAME) String token, @RequestBody IssueCategorySubmitRequest request) {
    return ok(issueCategorySubmitCommand.logic(request));
  }
}
