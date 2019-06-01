package pl.ee.common.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidationResponse {
  private String studentIndex;
  private List<Role> roles;

  public enum Role {
    ROLE_STUDENT, ROLE_EMPLOYEE
  }
}