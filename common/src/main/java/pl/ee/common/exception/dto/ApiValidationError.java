package pl.ee.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError implements ApiSubError {
  private String field;
  private String rejectedValue;
  private List<Map.Entry<ApiError.Language, String>> message;
}
