package com.example.rsiMiniProject.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Bean
  public Queue allLoggingQueue(@Value("${producer.names.all-logging.queue}") String queueName) {
    return new Queue(queueName, false);
  }

  @Bean
  public Queue partialLoggingQueue(
      @Value("${producer.names.partial-logging.queue}") String queueName) {
    return new Queue(queueName, false);
  }

  @Bean
  public DirectExchange partialLoggingExchange(
      @Value("${producer.names.partial-logging.exchange}") String exchangeName) {
    return new DirectExchange(exchangeName, false, true);
  }

  @Bean
  public String partialRoutingKey(
      @Value("${producer.names.partial-logging.routing-key}") String partialRoutingKey) {
    return partialRoutingKey;
  }

  @Bean
  public Binding partialBinding(
      DirectExchange partialLoggingExchange, Queue partialLoggingQueue, String partialRoutingKey) {
    return BindingBuilder.bind(partialLoggingQueue)
        .to(partialLoggingExchange)
        .with(partialRoutingKey);
  }

  @Bean
  public Queue imageQueue(@Value("${producer.names.image.queue}") String queueName) {
    return new Queue(queueName, false);
  }
}
