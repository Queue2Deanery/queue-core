package pl.ee.internal.application.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.ee.common.security.jwt.JwtAuthenticationFilter;
import pl.ee.common.security.jwt.JwtAuthenticationProvider;
import pl.ee.common.security.jwt.JwtAuthorizationFilter;
import pl.ee.common.security.jwt.JwtLogoutHandler;

import static pl.ee.common.security.SecurityConstants.LOGOUT_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .headers().cacheControl().disable().and().requestCache().disable()
      .cors().and().csrf().disable()
      .authorizeRequests().antMatchers("/**").permitAll()
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
