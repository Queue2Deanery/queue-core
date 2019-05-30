package pl.ee.authentication.domain.security.usecase;

import io.vavr.collection.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.ee.authentication.domain.security.TokenUtils;
import pl.ee.common.domain.security.dto.TokenGenerationRequest;
import pl.ee.common.domain.security.dto.TokenGenerationResponse;

@Service
public class TokenGenerationQuery {
  private TokenUtils tokenUtils;

  private AuthenticationManager authenticationManager;

  private String jwtSecret;

  private int jwtExpirationInMs;

  @Autowired
  public TokenGenerationQuery(TokenUtils tokenUtils, AuthenticationManager authenticationManager, @Value("${app.jwtSecret}") String jwtSecret, @Value("${app.jwtExpirationInMs}") int jwtExpirationInMs) {
    this.tokenUtils = tokenUtils;
    this.authenticationManager = authenticationManager;
    this.jwtSecret = jwtSecret;
    this.jwtExpirationInMs = jwtExpirationInMs;
  }

  public TokenGenerationResponse logic(TokenGenerationRequest request) {
    var tokenProvider = tokenUtils.generateToken.apply(jwtSecret, jwtExpirationInMs);
    return Stream.of(request)
      .map((req) -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getStudentIndex(), req.getPassword())))
      .map(tokenProvider)
      .map(token -> TokenGenerationResponse.builder().token(token).build())
      .get();
  }


}