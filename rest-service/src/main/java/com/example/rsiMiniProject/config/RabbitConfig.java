package com.example.rsiMiniProject.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
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

  @Bean
  public Queue replyImageQueue(@Value("${producer.names.image.reply-queue}") String queueName) {
    return new Queue(queueName, false);
  }

  @Bean
  public TopicExchange imageExchange(
      @Value("${producer.names.image.exchange}") String exchangeName) {
    return new TopicExchange(exchangeName, false, true);
  }

  @Bean
  public Binding imageBinding(Queue imageQueue, TopicExchange imageExchange) {
    return BindingBuilder.bind(imageQueue).to(imageExchange).with(imageQueue.getName());
  }

  @Bean
  public Binding replyImageBinding(TopicExchange imageExchange, Queue replyImageQueue) {
    return BindingBuilder.bind(replyImageQueue).to(imageExchange).with(replyImageQueue.getName());
  }

  @Bean
  public RabbitTemplate template(ConnectionFactory connectionFactory) {
    return new RabbitTemplate(connectionFactory);
  }

  @Bean
  public RabbitTemplate replyImageRabbitTemplate(
      ConnectionFactory connectionFactory, Queue replyImageQueue) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setReplyAddress(replyImageQueue.getName());
    template.setReplyTimeout(6000);
    return template;
  }

  @Bean
  SimpleMessageListenerContainer replyContainer(
      ConnectionFactory connectionFactory,
      Queue replyImageQueue,
      RabbitTemplate replyImageRabbitTemplate) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(replyImageQueue.getName());
    container.setMessageListener(replyImageRabbitTemplate);
    return container;
  }
}
