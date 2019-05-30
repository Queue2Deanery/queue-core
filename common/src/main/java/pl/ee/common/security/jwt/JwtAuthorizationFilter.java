package pl.ee.common.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.ee.common.security.dto.TokenValidationRequest;
import pl.ee.common.security.dto.TokenValidationResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtApi jwtApi;


  public JwtAuthorizationFilter(AuthenticationManager authenticationManager, ApplicationContext applicationContext) {
    super(authenticationManager);
    this.jwtApi = applicationContext.getBean(JwtApi.class);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws IOException, ServletException {
    var authentication = getAuthentication(request, response);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    filterChain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) {
    var token = request.getHeader(TOKEN_HEADER_NAME);
    if (token == null || token.isEmpty()) {
      return null;
    }

    return jwtApi
      .tokenValidationRequest(TokenValidationRequest.builder().token(token).build())
      .map(res ->
        new JwtUsernamePasswordAuthenticationToken(
          res.getStudentIndex(),
          null,
          res.getRoles().stream()
            .map(TokenValidationResponse.Role::toString)
            .peek(log::debug)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList()),
          token
        )
      )
      .peekLeft(res -> response.setStatus(HttpServletResponse.SC_RESET_CONTENT))
      .getOrNull();
  }
}
