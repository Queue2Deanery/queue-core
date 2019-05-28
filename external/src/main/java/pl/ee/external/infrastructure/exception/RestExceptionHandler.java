package pl.ee.external.infrastructure.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.ee.external.infrastructure.exception.dto.ApiError;

import java.util.List;
import java.util.Map;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${exception.not-found.en}")
  String enNotFound;

  @Value("${exception.not-found.pl}")
  String plNotFound;

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




}
