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
@AllArgsConstructor
@NoArgsConstructor
public class UserDeleteIssueResponse {

  private Long queueId;

  private List<Map.Entry<ApiError.Language, String>> message = List.of(
    Map.entry(ApiError.Language.en, "Issue awaiting processing was deleted successfully"),
    Map.entry(ApiError.Language.pl, "Zgłoszenie oczekujące na przyjęcie przez dziekanat zostało usunięte")
  );
}
