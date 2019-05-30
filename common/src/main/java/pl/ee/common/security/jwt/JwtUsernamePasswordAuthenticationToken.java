package pl.ee.common.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

  private String token;

  JwtUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
    super(principal, credentials, authorities);
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
