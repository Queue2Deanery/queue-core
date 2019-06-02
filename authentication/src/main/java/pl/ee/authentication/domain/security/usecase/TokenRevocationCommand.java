package pl.ee.authentication.domain.security.usecase;

import io.vavr.Tuple;
import io.vavr.collection.Stream;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.ee.authentication.domain.security.TokenUtils;
import pl.ee.common.exception.BusinessException;
import pl.ee.common.security.dto.TokenRevocationRequest;
import pl.ee.common.security.dto.TokenRevocationResponse;
import pl.ee.authentication.domain.model.db.RevokedJwtTokenEntity;
import pl.ee.authentication.infrastructure.repository.db.RevokedJwtTokenRepository;

@Service
public class TokenRevocationCommand {
  private RevokedJwtTokenRepository revokedJwtTokenRepository;

  private TokenUtils tokenUtils;

  private String jwtSecret;

  @Autowired
  public TokenRevocationCommand(RevokedJwtTokenRepository revokedJwtTokenRepository, TokenUtils tokenUtils, @Value("${app.jwtSecret}") String jwtSecret) {
    this.revokedJwtTokenRepository = revokedJwtTokenRepository;
    this.tokenUtils = tokenUtils;
    this.jwtSecret = jwtSecret;
  }

  public TokenRevocationResponse logic(TokenRevocationRequest request) {
    var decryptToken = tokenUtils.decryptedToken.apply(jwtSecret);

    return Stream.of(request)
      .map((req) -> Tuple.of(req, decryptToken.apply(req.getToken())))
      .map(requestWithToken ->
        RevokedJwtTokenEntity.builder()
          .userIndex(requestWithToken._2().getBody().getSubject())
          .ipAddress(requestWithToken._1().getIpAddress())
          .jwtToken(requestWithToken._1().getToken())
          .build())
      .map((entity) -> Try.of(() -> revokedJwtTokenRepository.save(entity)).getOrElseThrow(() -> new BusinessException("Token revocation")).getCreatedAt())
      .map(createdAt -> TokenRevocationResponse.builder().createdAt(createdAt).build())
      .get();
  }
}
