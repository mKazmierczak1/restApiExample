package com.example.rsiMiniProject.rabbit;

import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImageRpcHandler {

  private final RabbitTemplate replyImageRabbitTemplate;
  private final Queue imageQueue;

  public byte[] getImage(String type, int id) {
    var imageIdMessage =
        MessageBuilder.withBody(String.join(".", String.valueOf(id), type).getBytes()).build();
    var result = replyImageRabbitTemplate.sendAndReceive(imageQueue.getName(), imageIdMessage);

    if (result != null) {
      String correlationId = imageIdMessage.getMessageProperties().getCorrelationId();
      HashMap<String, Object> headers =
          (HashMap<String, Object>) result.getMessageProperties().getHeaders();
      String msgId = (String) headers.get("spring_returned_message_correlation");
      if (msgId.equals(correlationId)) {
        return result.getBody();
      }
    }

    return null;
  }
}
