package pl.ee.authentication.domain.authentication.usecase;

import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.ee.authentication.domain.authentication.TokenUtils;
import pl.ee.authentication.domain.authentication.dto.TokenValidationRequest;
import pl.ee.authentication.domain.authentication.dto.TokenValidationResponse;
import pl.ee.authentication.infrastructure.repository.db.RevokedJwtTokenRepository;

import java.util.function.Predicate;

@Service
@Slf4j
public class TokenValidationQuery {


  private String jwtSecret;

  private RevokedJwtTokenRepository revokedJwtTokenRepository;

  private TokenUtils tokenUtils;

  @Autowired
  public TokenValidationQuery(RevokedJwtTokenRepository revokedJwtTokenRepository, TokenUtils tokenUtils, @Value("${app.jwtSecret}") String jwtSecret) {
    this.revokedJwtTokenRepository = revokedJwtTokenRepository;
    this.tokenUtils = tokenUtils;
    this.jwtSecret = jwtSecret;
  }

  public TokenValidationResponse logic(TokenValidationRequest request) {
    return Stream.of(request)
      .map(TokenValidationRequest::getToken)
      .filter(Predicate.not(revokedJwtTokenRepository::existsByJwtToken))
      .map((token) ->
          TokenValidationResponse.builder()
            .studentIndex(tokenUtils.decryptedToken.apply(jwtSecret, token).getBody().getSubject())
            .build()
      )
      .getOrElseThrow(RuntimeException::new);// TokenRevokedException
  }
}
