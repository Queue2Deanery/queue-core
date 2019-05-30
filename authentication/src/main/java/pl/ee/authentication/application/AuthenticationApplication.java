package pl.ee.authentication.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.ee.common.security.jwt.JwtApi;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.ee", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = JwtApi.class)})
@EntityScan(basePackages = "pl.ee.authentication.domain.model.db")
@EnableJpaRepositories(basePackages = "pl.ee.authentication.infrastructure.repository.db")
public class AuthenticationApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthenticationApplication.class, args);
  }
}
