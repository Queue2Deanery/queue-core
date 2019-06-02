package pl.ee.authentication.domain.security;

import io.jsonwebtoken.*;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.stereotype.Service;
import pl.ee.common.exception.AuthorizationException;
import pl.ee.common.security.dto.TokenValidationResponse;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        throw new AuthorizationException();
      }).get();

  public Function3<String, Integer,Authentication, String> generateToken = (jwtSecret, jwtExpirationInMs, authentication) -> {

    LdapUserDetailsImpl userPrincipal = (LdapUserDetailsImpl) authentication.getPrincipal();
    var roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

    return Jwts.builder()
      .setSubject(userPrincipal.getUsername())
      .setIssuedAt(now)
      .setExpiration(expiryDate)
      .claim("roles", roles) // todo add retrieving user groups from ldap
      .signWith(SignatureAlgorithm.HS512, jwtSecret)
      .compact();
  };
}
