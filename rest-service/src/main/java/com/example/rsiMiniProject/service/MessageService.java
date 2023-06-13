package com.example.rsiMiniProject.service;

import com.example.rsiMiniProject.producer.MessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

  private final MessageProducer producer;

  public void sendMessage(String message) {
    producer.sendMessage(message);
  }
}
