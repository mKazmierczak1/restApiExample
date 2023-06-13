package com.example.rsiMiniProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FullLoggingService {

  @RabbitListener(queues = "${consumer.names.all-logging.queue}")
  public void listen(String in) {
    log.info("Message read from queue: {}", in);
  }
}
