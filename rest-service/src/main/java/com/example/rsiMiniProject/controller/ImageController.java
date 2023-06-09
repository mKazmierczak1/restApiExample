package com.example.rsiMiniProject.controller;

import com.example.rsiMiniProject.exceptions.ImageNotFoundException;
import com.example.rsiMiniProject.service.ImageService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

  private final ImageService imageService;

  @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
  public @ResponseBody byte[] getImageById(@PathVariable Integer id) throws ImageNotFoundException {
    return imageService.getImageById(id);
  }

  @GetMapping(value = "/gif/{id}", produces = MediaType.IMAGE_GIF_VALUE)
  public @ResponseBody byte[] getGifById(@PathVariable Integer id) throws ImageNotFoundException {
    return imageService.getGifById(id);
  }

  @PostMapping("/image")
  public void handleImageUpload(@RequestParam("file") MultipartFile image) throws IOException {
    imageService.store(image);
  }
}
