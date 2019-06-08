package pl.ee.external.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ee.common.exception.dto.ApiError;

import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmitIssueResponse {

  private Long issueId;

  private List<Map.Entry<ApiError.Language, String>> message = List.of(
    Map.entry(ApiError.Language.en, "You submitted issue successfully"),
    Map.entry(ApiError.Language.pl, "Zgłosiłeś się do kolejki pomyślnie")
  );
}
