package pl.ee.external.application;

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
@EnableJpaRepositories(basePackages = "pl.ee.external.infrastructure.repository")
public class ExternalApplication {
      public static void main(String[] args) {
        SpringApplication.run(ExternalApplication.class, args);
    }
}
