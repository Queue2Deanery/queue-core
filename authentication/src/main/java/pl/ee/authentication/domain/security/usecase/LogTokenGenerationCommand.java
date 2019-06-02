package pl.ee.authentication.domain.security.usecase;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pl.ee.authentication.domain.model.db.TokenGenerationAuditEntity;
import pl.ee.authentication.infrastructure.repository.db.TokenGenerationAuditRepository;
import pl.ee.common.mail.MailConstants;
import pl.ee.common.mail.dto.TokenGenerationMessageRequest;
import pl.ee.common.security.dto.TokenGenerationRequest;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LogTokenGenerationCommand {

  private RabbitTemplate rabbitTemplate;

  private TokenGenerationAuditRepository tokenGenerationAuditRepository;

  public void logic(TokenGenerationRequest request) {
    if (request.getIpAddress() == null && request.getUserIndex() == null) {
      return;
    }
    var entities = tokenGenerationAuditRepository.findAllByUserIndex(request.getUserIndex());

    var isIpRegistered = entities.isEmpty() || entities.stream().map(TokenGenerationAuditEntity::getIpAddress).collect(Collectors.toList()).contains(request.getIpAddress());

    if (!isIpRegistered) {
      var messageRequest = TokenGenerationMessageRequest.builder()
        .ipAddress(request.getIpAddress())
        .userIndex(request.getUserIndex())
        .userMail("jakub.k.b@hotmail.com")
        .build();

      rabbitTemplate.convertAndSend(MailConstants.TOKEN_GENERATION_QUEUE_NAME, messageRequest);
    }

    tokenGenerationAuditRepository.save(TokenGenerationAuditEntity.builder().ipAddress(request.getIpAddress()).userIndex(request.getUserIndex()).build());
  }
}
