package pl.ee.authentication.domain.authentication;

import io.jsonwebtoken.*;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Slf4j
@Service
public class TokenUtils {

  public Function2<String, String, Jws<Claims>> decryptedToken = (jwtSecret, token) ->
    Try.of(() ->
      Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token))
      .onFailure(throwable -> {
        Match(throwable).of(
          Case($(instanceOf(SignatureException.class)), () -> run(() -> log.error("Invalid jwt signature"))),
          Case($(instanceOf(MalformedJwtException.class)), () -> run(() -> log.error("Malformed jwt"))),
          Case($(instanceOf(ExpiredJwtException.class)), () -> run(() -> log.error("Expired jwt"))),
          Case($(instanceOf(UnsupportedJwtException.class)), () -> run(() -> log.error("Unsupported jwt"))),
          Case($(instanceOf(IllegalArgumentException.class)), () -> run(() -> log.error("Jwt claims string is empty ")))
        );
        throw new RuntimeException(); // change to InvalidTokenException
      }).get();

  public Function3<String, Integer,Authentication, String> generateToken = (jwtSecret, jwtExpirationInMs, authentication) -> {

    LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    return Jwts.builder()
      .setSubject(userPrincipal.getUsername())
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  };
}
