package pl.ee.common.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import pl.ee.common.security.dto.TokenRevocationRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

@Slf4j
public class JwtLogoutHandler  implements LogoutHandler {

  private JwtApi jwtApi;

  public JwtLogoutHandler(ApplicationContext applicationContext) {
    this.jwtApi = applicationContext.getBean(JwtApi.class);
  }

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    var token = request.getHeader(TOKEN_HEADER_NAME);
    if(token == null || token.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return;
    }

    jwtApi.tokenRevocationRequest(TokenRevocationRequest.builder().token(token).ipAddress(request.getRemoteAddr()).build());
  }
}
