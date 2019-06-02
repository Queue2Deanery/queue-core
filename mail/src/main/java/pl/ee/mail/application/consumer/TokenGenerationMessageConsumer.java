package pl.ee.mail.application.consumer;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pl.ee.common.mail.MailConstants;
import pl.ee.common.mail.dto.TokenGenerationMessageRequest;
import pl.ee.mail.infrastructure.service.MailService;

@Service
@Slf4j
@AllArgsConstructor
public class TokenGenerationMessageConsumer {

  private MailService mailService;

  @RabbitListener(queues = {MailConstants.TOKEN_GENERATION_QUEUE_NAME})
  public void receiveMessage(TokenGenerationMessageRequest request) {
    log.debug("Received message request: " + request.toString());
    mailService.sendMail(request);
  }
}
