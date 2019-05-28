package pl.ee.authentication.application;

  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.boot.autoconfigure.domain.EntityScan;
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
  import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.ee")
@EntityScan(basePackages = "pl.ee.authentication.domain.model.db")
@EnableJpaRepositories(basePackages = "pl.ee.authentication.infrastructure.repository.db")
public class AuthenticationApplication {
  public static void main(String[] args) {
    SpringApplication.run(AuthenticationApplication.class, args);
  }
}
