package pl.ee.internal.domain.issue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.ee.common.exception.dto.ApiError;

import java.util.List;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueRemoveResponse {
  private Long issueId;

  private List<Map.Entry<ApiError.Language, String>> message = List.of(
    Map.entry(ApiError.Language.en, "You successfuly removed issue"),
    Map.entry(ApiError.Language.pl, "Usunąłeś zgłoszenie pomyślnie")
  );
}
