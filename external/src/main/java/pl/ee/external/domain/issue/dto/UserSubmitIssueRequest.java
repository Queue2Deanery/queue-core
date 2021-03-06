package pl.ee.external.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmitIssueRequest {
  @NotNull
  private Long queueId;
  @NotNull
  private Long issueCategoryId;
  @NotNull
  private String studentComment;
}
