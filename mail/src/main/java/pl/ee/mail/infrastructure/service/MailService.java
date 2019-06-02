package pl.ee.mail.infrastructure.service;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.ee.common.mail.dto.TokenGenerationMessageRequest;

@Slf4j
@Service
public class MailService {

  @Value("${app.system-mail}")
  private String systemMail;

  @Value("${app.message.suspicious-login.subject}")
  private String subject;

  @Value("${app.message.suspicious-login.body}")
  private String body;

  private JavaMailSender javaMailSender;

  @Autowired
  MailService(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendMail(TokenGenerationMessageRequest request) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(request.getUserMail());
    mailMessage.setSubject(subject);
    mailMessage.setText(body
      .replace("{userIndex}", request.getUserIndex())
      .replace("{ipAddress}", request.getIpAddress()));
    mailMessage.setFrom(systemMail);

    Try.run(() -> javaMailSender.send(mailMessage))
      .onFailure(throwable -> log.error("sending mail failed: {}", throwable.getMessage()));
  }
}

