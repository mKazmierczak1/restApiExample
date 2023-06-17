package com.example.rsiMiniProject.rabbit;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  private static final Path SERVER_BASE_PATH = Paths.get("rest-service/src/main/resources/img");

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

  @RabbitListener(queues = "${producer.names.image.store-queue}")
  public void store(Message message) throws IOException {
    var outputStream = getFilePath();
    outputStream.write(message.getBody());
    outputStream.flush();
    outputStream.close();

    log.info("New image saved!");
  }

  private OutputStream getFilePath() throws IOException {
    var fileName = "3.jpg";
    return Files.newOutputStream(SERVER_BASE_PATH.resolve(fileName));
  }
}
