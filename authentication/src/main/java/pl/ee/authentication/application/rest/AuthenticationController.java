package pl.ee.authentication.application.rest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ee.authentication.domain.security.usecase.LogTokenGenerationCommand;
import pl.ee.common.security.dto.*;
import pl.ee.authentication.domain.security.usecase.TokenGenerationQuery;
import pl.ee.authentication.domain.security.usecase.TokenRevocationCommand;
import pl.ee.authentication.domain.security.usecase.TokenValidationQuery;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/token")
public class AuthenticationController {

  private TokenRevocationCommand tokenRevocationCommand;

  private TokenValidationQuery tokenValidationQuery;

  private TokenGenerationQuery tokenGenerationQuery;

  private LogTokenGenerationCommand logTokenGenerationCommand;

  @PostMapping(path = "/generate")
  public ResponseEntity<TokenGenerationResponse> generateToken(@RequestBody TokenGenerationRequest requestBody, HttpServletRequest request) {
    logTokenGenerationCommand.logic(requestBody);
    return ResponseEntity.ok(tokenGenerationQuery.logic(requestBody));
  }

  @PostMapping(path = "/validate")
  public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody TokenValidationRequest requestBody, HttpServletRequest request) {
    return ResponseEntity.ok(tokenValidationQuery.logic(requestBody));
  }

  @PostMapping(path = "/revoke")
  public ResponseEntity<TokenRevocationResponse> revokeToken(@RequestBody TokenRevocationRequest requestBody, HttpServletRequest request) {
    return ResponseEntity.ok(tokenRevocationCommand.logic(requestBody));
  }

}
