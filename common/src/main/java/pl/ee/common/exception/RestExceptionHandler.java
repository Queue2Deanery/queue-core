package pl.ee.common.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.ee.common.exception.dto.ApiError;

import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${exception.not-found.en}")
  String enNotFound;

  @Value("${exception.not-found.pl}")
  String plNotFound;

  @Value("${exception.business.en}")
  String enBusiness;

  @Value("${exception.business.pl}")
  String plBusiness;

  @Value("${exception.validation.en}")
  String enValidation;

  @Value("${exception.validation.pl}")
  String plValidation;

  @Value("${exception.authentication.en}")
  String enAuthentication;

  @Value("${exception.authentication.pl}")
  String plAuthentication;

  @Value("${exception.authorization.en}")
  String enAuthorization;

  @Value("${exception.authorization.pl}")
  String plAuthorization;

  @ExceptionHandler(EntityNotFoundException.class)
  protected ResponseEntity<ApiError> handleEntityNotFound(EntityNotFoundException ex) {
    var response = ApiError.builder()
      .httpStatus(HttpStatus.NOT_FOUND)
      .message(List.of(
        Map.entry(ApiError.Language.en, enNotFound.replace("{}", ex.getMessage())),
        Map.entry(ApiError.Language.pl, plNotFound.replace("{}", ex.getMessage()))
      )).build();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ApiError> handleBusinessException(BusinessException ex) {
    var response = ApiError.builder()
      .httpStatus(HttpStatus.CONFLICT)
      .message(List.of(
        Map.entry(ApiError.Language.en, enBusiness.replace("{}", ex.getMessage())),
        Map.entry(ApiError.Language.pl, plBusiness.replace("{}", ex.getMessage()))
      )).build();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @ExceptionHandler(ValidationException.class)
  protected ResponseEntity<ApiError> handleValidationException(ValidationException ex) {
    var response = ApiError.builder()
      .httpStatus(HttpStatus.BAD_REQUEST)
      .message(List.of(
        Map.entry(ApiError.Language.en, enValidation),
        Map.entry(ApiError.Language.pl, plValidation)
      )).subErrors(ex.getValidationErrors()).build();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @ExceptionHandler(AuthenticationException.class)
  protected ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException ex) {
    var response = ApiError.builder()
      .httpStatus(HttpStatus.FORBIDDEN)
      .message(List.of(
        Map.entry(ApiError.Language.en, enAuthentication.replace("{}", ex.getMessage())),
        Map.entry(ApiError.Language.pl, plAuthentication.replace("{}", ex.getMessage()))
      )).build();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

  @ExceptionHandler(AuthorizationException.class)
  protected ResponseEntity<ApiError> handleAuthorizationException(AuthorizationException ex) {
    var response = ApiError.builder()
      .httpStatus(HttpStatus.UNAUTHORIZED)
      .message(List.of(
        Map.entry(ApiError.Language.en, enAuthorization.replace("{}", ex.getMessage())),
        Map.entry(ApiError.Language.pl, plAuthorization.replace("{}", ex.getMessage()))
      )).build();
    return ResponseEntity.status(response.getHttpStatus()).body(response);
  }

}
