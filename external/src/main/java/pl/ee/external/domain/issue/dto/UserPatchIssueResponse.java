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
public class UserPatchIssueResponse {

  private Long issueId;

  private List<Map.Entry<ApiError.Language, String>> message = List.of(
    Map.entry(ApiError.Language.en, "You updated issue successfully"),
    Map.entry(ApiError.Language.pl, "Zaktualizowałeś zgłoszenie do kolejki pomyślnie")
  );
}
