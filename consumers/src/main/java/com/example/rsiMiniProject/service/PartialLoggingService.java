package com.example.rsiMiniProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PartialLoggingService {

  @RabbitListener(
      bindings =
          @QueueBinding(
              value = @Queue(value = "${consumer.names.partial-logging.queue}", durable = "false"),
              exchange =
                  @Exchange(
                      value = "${consumer.names.partial-logging.exchange}",
                      ignoreDeclarationExceptions = "true",
                      durable = "false",
                      autoDelete = "true"),
              key = "${consumer.names.partial-logging.routing-key}"))
  public void listen(String in) {
    log.info("Message read from queue: {}", in);
  }
}
