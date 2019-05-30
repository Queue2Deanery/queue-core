package pl.ee.external.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.ee.external.application.security.JwtAuthenticationFilter;
import pl.ee.external.application.security.JwtAuthenticationProvider;
import pl.ee.external.application.security.JwtAuthorizationFilter;
import pl.ee.external.application.security.JwtLogoutHandler;

import static pl.ee.common.domain.security.SecurityConstants.LOGOUT_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .cors().and().csrf().disable()
      .authorizeRequests().antMatchers("/").permitAll()
      .and()
      .addFilter(new JwtAuthenticationFilter(authenticationManager(), getApplicationContext()))
      .addFilter(new JwtAuthorizationFilter(authenticationManager(), getApplicationContext()))
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .logout()
      .addLogoutHandler(new JwtLogoutHandler(getApplicationContext()))
      .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL, HttpMethod.GET.name()));
  }


  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(new JwtAuthenticationProvider(getApplicationContext()));
  }
}
