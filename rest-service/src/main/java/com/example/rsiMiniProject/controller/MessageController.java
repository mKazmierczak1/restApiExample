package com.example.rsiMiniProject.controller;

import com.example.rsiMiniProject.exeptions.IncorrectNewImageException;
import com.example.rsiMiniProject.model.TextMessage;
import com.example.rsiMiniProject.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageController {

  private final MessageService messageService;

  @PostMapping("/message")
  void sendMessage(@RequestBody TextMessage message) {
    var text = message.getText();

    if (text == null || text.isEmpty()) {
      throw new IncorrectNewImageException();
    }

    messageService.sendMessage(text);
  }

  @GetMapping("/message")
  TextMessage m() {
    return new TextMessage("abc");
  }
}
