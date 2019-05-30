package pl.ee.common.exception;

import lombok.Getter;
import pl.ee.common.exception.dto.ApiValidationError;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
  private List<ApiValidationError> validationErrors;

  public ValidationException(List<ApiValidationError> validationErrors) {
    super();
    this.validationErrors = validationErrors;
  }
}
