package pl.ee.external.infrastructure.exception.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Data
public class ApiError {

  private HttpStatus httpStatus;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
  @Builder.Default
  private LocalDateTime timestamp = LocalDateTime.now();
  private List<Map.Entry<Language, String>> message;
  private List<ApiSubError> subErrors;

  public enum Language {
    pl, en
  }


}
