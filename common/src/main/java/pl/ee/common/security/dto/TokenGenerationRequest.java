package pl.ee.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenGenerationRequest {
  private String userIndex;
  private String password;
  private String ipAddress;
}
