package pl.ee.external.infrastructure.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ee.external.domain.issue.dto.BasicIssueResponse;

import java.util.List;

@RestController
@RequestMapping(path = "/issue")
public class IssueController {

  @GetMapping
  public ResponseEntity<List<BasicIssueResponse>> getActiveIssueListByQueueId(@PathVariable Long queueId) {
    return ResponseEntity.ok(List.of(BasicIssueResponse.builder().build()));
  }
}
