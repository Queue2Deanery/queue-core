package pl.ee.external.domain.issue.usecase;

import org.springframework.stereotype.Service;
import pl.ee.external.domain.issue.dto.BasicIssueResponse;

import java.util.List;

@Service
public class ListBasicIssueQuery {
  public List<BasicIssueResponse> logic(Long queueId) {
    return null;
  }
}
