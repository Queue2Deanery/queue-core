package pl.ee.external.application.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.rcp.RemoteAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.ee.common.domain.security.SecurityConstants;
import pl.ee.common.domain.security.dto.TokenGenerationRequest;
import pl.ee.common.domain.security.dto.TokenGenerationResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static pl.ee.common.domain.security.SecurityConstants.TOKEN_HEADER_NAME;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private JwtApi jwtApi;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext) {
    this.authenticationManager = authenticationManager;
    this.jwtApi = applicationContext.getBean(JwtApi.class);

    setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    var userIndex = request.getParameter("userIndex");
    var password = request.getParameter("password");

    return jwtApi.tokenGenerationRequest(TokenGenerationRequest.builder().studentIndex(userIndex).password(password).build())
      .map(TokenGenerationResponse::getToken)
      .map( token -> new JwtUsernamePasswordAuthenticationToken(userIndex, password, null, token))
      .map(authenticationManager::authenticate).getOrElseThrow((throwable -> new RemoteAuthenticationException(throwable.getMessage())));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

    var token = ((JwtUsernamePasswordAuthenticationToken) authResult).getToken();

    log.debug("token: " + token);
    response.addHeader(TOKEN_HEADER_NAME, token);
  }
}
