package pl.ee.authentication.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ee.authentication.domain.authentication.dto.*;
import pl.ee.authentication.domain.authentication.usecase.TokenGenerationQuery;
import pl.ee.authentication.domain.authentication.usecase.TokenRevocationCommand;
import pl.ee.authentication.domain.authentication.usecase.TokenValidationQuery;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/authorization/token")
public class AuthenticationController {

  private TokenRevocationCommand tokenRevocationCommand;

  private TokenValidationQuery tokenValidationQuery;

  private TokenGenerationQuery tokenGenerationQuery;

  @Autowired
  AuthenticationController(TokenRevocationCommand revocationCommand, TokenValidationQuery validationQuery, TokenGenerationQuery generationQuery ) {
    this.tokenRevocationCommand = revocationCommand;
    this.tokenValidationQuery = validationQuery;
    this.tokenGenerationQuery = generationQuery;
  }

  @PostMapping(path = "/generate")
  public ResponseEntity<TokenGenerationResponse> generateToken(@RequestBody TokenGenerationRequest requestBody, HttpServletRequest request) {
    return ResponseEntity.ok(tokenGenerationQuery.logic(requestBody));
  }

  @PostMapping(path = "/validate")
  public ResponseEntity<TokenValidationResponse> validateToken(@RequestBody TokenValidationRequest requestBody, HttpServletRequest request) {
    return ResponseEntity.ok(tokenValidationQuery.logic(requestBody));
  }

  @DeleteMapping
  public ResponseEntity<TokenRevocationResponse> revokeToken(@RequestBody TokenRevocationRequest requestBody, HttpServletRequest request) {
    return ResponseEntity.ok(tokenRevocationCommand.logic(requestBody));
  }

}
