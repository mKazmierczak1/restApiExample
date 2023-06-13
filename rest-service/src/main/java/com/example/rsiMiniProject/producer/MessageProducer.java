package com.example.rsiMiniProject.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {

  private final DirectExchange loggingExchange;
  private final Queue allLoggingQueue;
  private final RabbitTemplate template;
  private final String partialRoutingKey;

  public void sendMessage(String message) {
    sendAll(message);

    if (message.contains("message")) {
      sendPartial(message);
    }
  }

  private void sendAll(Object message) {
    template.convertAndSend(allLoggingQueue.getName(), message);
  }

  private void sendPartial(Object message) {
    template.convertAndSend(loggingExchange.getName(), partialRoutingKey, message);
  }
}
