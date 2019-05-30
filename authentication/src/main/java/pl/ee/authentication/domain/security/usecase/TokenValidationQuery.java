package pl.ee.authentication.domain.security.usecase;

import io.vavr.collection.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.ee.authentication.domain.security.TokenUtils;
import pl.ee.common.domain.security.dto.TokenValidationRequest;
import pl.ee.common.domain.security.dto.TokenValidationResponse;
import pl.ee.authentication.infrastructure.repository.db.RevokedJwtTokenRepository;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static io.vavr.API.*;

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
      .map((token) -> {
          var decryptedToken = tokenUtils.decryptedToken.apply(jwtSecret, token);
          return TokenValidationResponse.builder()
            .studentIndex(decryptedToken.getBody().getSubject())
            .roles(((List<?>) decryptedToken.getBody().get("roles")).stream().map(roleMapper
            ).collect(Collectors.toList())).build();
        }
      )
      .getOrElseThrow(RuntimeException::new);// TokenRevokedException
  }

  private Function<Object, TokenValidationResponse.Role> roleMapper = role ->
    Match((String) role).of(
      Case($("STUDENT"), r -> TokenValidationResponse.Role.STUDENT),
      Case($("EMPLOYEE"), r -> TokenValidationResponse.Role.EMPLOYEE)
    );
}
