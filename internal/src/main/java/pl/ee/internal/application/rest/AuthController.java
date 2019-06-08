package pl.ee.internal.application.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import static pl.ee.common.security.SecurityConstants.TOKEN_HEADER_NAME;

// A hack to use auth endpoints from SecurityConfiguration in swagger

@RequestMapping("/auth")
public class AuthController {
  @ApiOperation("Login.")
  @PostMapping("/login")
  public void fakeLogin(@ApiParam("User Index") @RequestParam String userIndex, @ApiParam("Password") @RequestParam String password) {
    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
  }

  @ApiOperation("Logout.")
  @GetMapping("/logout")
  public void fakeLogout(@RequestHeader(value = TOKEN_HEADER_NAME) String token) {
    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
  }
}
