package pl.ee.internal.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "pl.ee")
@EntityScan(basePackages = "pl.ee.common.model")
@EnableJpaRepositories(basePackages = "pl.ee.internal.infrastructure.repository")
public class InternalApplication {
  public static void main(String[] args) {
    SpringApplication.run(InternalApplication.class, args);
  }
}
