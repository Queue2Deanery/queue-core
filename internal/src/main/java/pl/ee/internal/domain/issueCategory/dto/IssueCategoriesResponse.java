package pl.ee.internal.domain.issueCategory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueCategoriesResponse {

  List<IssueCategory> issueCategories;

  @Builder
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class IssueCategory {
    Long id;
    String name;
  }
}
