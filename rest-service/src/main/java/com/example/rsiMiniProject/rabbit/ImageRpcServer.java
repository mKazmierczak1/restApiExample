package com.example.rsiMiniProject.rabbit;

import java.io.IOException;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageRpcServer {

  private final RabbitTemplate template;
  private final TopicExchange imageExchange;
  private final Queue replyImageQueue;

  @RabbitListener(queues = "${producer.names.image.queue}")
  public void listen(Message message) throws IOException {
    var imageName = new String(message.getBody());
    Resource resource = new ClassPathResource("img/" + imageName);
    byte[] image = new byte[] {};

    log.info("Received request with filename: {}", imageName);

    if (resource.exists()) {
      image = Files.readAllBytes(resource.getFile().toPath());
      log.info("Image found!");
    } else {
      log.error("Image not found!");
    }

    Message build = MessageBuilder.withBody(image).build();
    CorrelationData correlationData =
        new CorrelationData(message.getMessageProperties().getCorrelationId());

    template.sendAndReceive(
        imageExchange.getName(), replyImageQueue.getName(), build, correlationData);
  }
}
