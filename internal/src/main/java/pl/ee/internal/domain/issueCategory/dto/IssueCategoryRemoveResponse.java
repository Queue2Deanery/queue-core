package pl.ee.internal.domain.issueCategory.dto;

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
public class IssueCategoryRemoveResponse {
  private Long issueCategoryId;

  private List<Map.Entry<ApiError.Language, String>> message = List.of(
    Map.entry(ApiError.Language.en, "You deleted issue category successfully"),
    Map.entry(ApiError.Language.pl, "Usunąłeś kategorię zgłoszenia pomyślnie")
  );
}
