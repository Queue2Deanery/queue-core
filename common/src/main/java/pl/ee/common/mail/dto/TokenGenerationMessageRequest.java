package pl.ee.common.mail.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenGenerationMessageRequest implements Serializable {
  @JsonProperty
  private String userMail;
  @JsonProperty
  private String userIndex;
  @JsonProperty
  private String ipAddress;
}
