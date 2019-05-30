package pl.ee.external.infrastructure.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

// A hack to use auth endpoints from SecurityConfiguration in swagger

@RestController
@RequestMapping("/auth")
public class AuthController {
  @ApiOperation("Login.")
  @PostMapping("/login")
  public void fakeLogin(@ApiParam("User Index") @RequestParam String userIndex, @ApiParam("Password") @RequestParam String password) {
    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
  }

  @ApiOperation("Logout.")
  @GetMapping("/logout")
  public void fakeLogout() {
    throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
  }
}
