package pl.ee.external.application.security;

import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.rcp.RemoteAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.ee.common.domain.security.dto.TokenValidationRequest;
import pl.ee.common.domain.security.dto.TokenValidationResponse;

import java.util.stream.Collectors;

public class JwtAuthenticationProvider implements AuthenticationProvider {

  private JwtApi jwtApi;

  public JwtAuthenticationProvider(ApplicationContext applicationContext) {
    this.jwtApi = applicationContext.getBean(JwtApi.class);
  }


  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var jwtAuthentication = (JwtUsernamePasswordAuthenticationToken) authentication;
    var name = jwtAuthentication.getName();
    var token = jwtAuthentication.getToken(); // token is password at the same time :D

    return jwtApi.tokenValidationRequest(TokenValidationRequest.builder().token(token).build())
      .map(response -> new JwtUsernamePasswordAuthenticationToken(name, jwtAuthentication.getCredentials(), response.getRoles().stream()// gold medal solution xDDDD beacause password is protected outside of provider
        .map(TokenValidationResponse.Role::toString)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList()), token)).getOrElseThrow(throwable -> new RemoteAuthenticationException(throwable.getMessage()));
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
