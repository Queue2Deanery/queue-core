package pl.ee.external.application.security;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.ee.common.domain.security.dto.*;

import java.time.Duration;

@Component
@Slf4j
public class JwtApi {

//  @Value("app.authenticationUri")
  String authenticationUri = "http://localhost:8089/api/v1.0";

  private final RestTemplate restTemplate;

  private final Retry retry = Retry.of("SecurityApi", RetryConfig.custom()
    .maxAttempts(3)
    .waitDuration(Duration.ofMillis(200))
    .retryExceptions(RestClientException.class)
    .build());

  @Autowired
  public JwtApi(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.rootUri(authenticationUri).build();
  }

  Either<Throwable, TokenGenerationResponse> tokenGenerationRequest(TokenGenerationRequest request) {
    return Try.of(
      Retry.decorateCheckedSupplier(retry,
        CheckedFunction0.of(() -> restTemplate.postForEntity("/token/generate", request, TokenGenerationResponse.class))))
      .map(ResponseEntity::getBody).toEither()
      .peek(response -> log.debug("generate jwt success: {}", response))
      .peekLeft(throwable -> log.error("failed to generate token", throwable));
  }

  Either<Throwable, TokenRevocationResponse> tokenRevocationRequest(TokenRevocationRequest request) {
    return Try.of(
      Retry.decorateCheckedSupplier(retry,
        CheckedFunction0.of(() -> restTemplate.postForEntity("/token/revoke", request, TokenRevocationResponse.class))))
      .map(ResponseEntity::getBody).toEither()
      .peek(response -> log.debug("revoke jwt success: {}", response))
      .peekLeft(throwable -> log.error("failed to revoke token", throwable));
  }

  Either<Throwable, TokenValidationResponse> tokenValidationRequest(TokenValidationRequest request) {
    return Try.of(
      Retry.decorateCheckedSupplier(retry,
        CheckedFunction0.of(() -> restTemplate.postForEntity("/token/validate", request, TokenValidationResponse.class))))
      .map(ResponseEntity::getBody).toEither()
      .peek(response -> log.debug("validate jwt success: {}", response))
      .peekLeft(throwable -> log.error("failed to validate token", throwable));
  }

}
