package pl.ee.authentication.application.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  securedEnabled = true,
  jsr250Enabled = true,
  prePostEnabled = true
)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Value("${app.ldap.url}")
  private String ldapUrl;

  @Value("${app.ldap.base-dn}")
  private String ldapBaseDn;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf()
      .disable()
      .cors()
      .disable()
      .exceptionHandling()
      .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
      .and()
      .authorizeRequests()
      .antMatchers("/**").permitAll()
      .anyRequest().authenticated();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .ldapAuthentication()
      .groupRoleAttribute("userRole")
      .userDnPatterns("uid={0},ou=people")
      .groupSearchBase("ou=groups")
      .contextSource(contextSource())
      .passwordCompare()
      // todo encrypt password with bcrypt -> hash with sha -> ldap compare then xDDD
      .passwordEncoder( new LdapShaPasswordEncoder())
      .passwordAttribute("userPassword");
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public DefaultSpringSecurityContextSource contextSource() {
    return  new DefaultSpringSecurityContextSource(
      Collections.singletonList(ldapUrl), ldapBaseDn);
  }


}
