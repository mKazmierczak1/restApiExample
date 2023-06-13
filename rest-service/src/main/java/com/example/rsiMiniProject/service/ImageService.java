package com.example.rsiMiniProject.service;

import com.example.rsiMiniProject.exceptions.ImageNotFoundException;
import com.example.rsiMiniProject.rabbit.ImageRpcHandler;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRpcHandler imageRpcHandler;
  private final RabbitTemplate template;
  private final Queue storeImageQueue;

  public byte[] getImageById(int id) throws ImageNotFoundException {
    return imageRpcHandler.getImage("jpg", id);
  }

  public byte[] getGifById(int id) throws ImageNotFoundException {
    return imageRpcHandler.getImage("gif", id);
  }

  public void store(MultipartFile newImage) throws IOException {
    template.send(storeImageQueue.getName(), new Message(newImage.getBytes()));
  }
}
