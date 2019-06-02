package pl.ee.mail.application.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static pl.ee.common.mail.MailConstants.TOKEN_GENERATION_EXCHANGE_NAME;
import static pl.ee.common.mail.MailConstants.TOKEN_GENERATION_QUEUE_NAME;

@Configuration
public class AmqpConfiguration {

  private final static String queueName = TOKEN_GENERATION_QUEUE_NAME;
  private final static String exchangeName = TOKEN_GENERATION_EXCHANGE_NAME;

  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange(exchangeName);
  }

  @Bean
  Binding binding() {
    return BindingBuilder.bind(queue()).to(exchange()).with(queueName);
  }

  @Bean
  RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
    return rabbitTemplate;
  }

  @Bean
  Jackson2JsonMessageConverter producerJackson2MessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

}
