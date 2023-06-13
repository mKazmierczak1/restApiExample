package com.example.rsiMiniProject.controller;

import com.example.rsiMiniProject.exeptions.ImageNotFoundException;
import com.example.rsiMiniProject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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
  //
  //  @PostMapping("/image")
  //  Image newImage(@RequestBody Image newImage) {
  //    if (newImage.getFirstName().isEmpty() || newImage.getLastName().isEmpty()) {
  //      throw new IncorrectNewImageException();
  //    }
  //
  //    return imageService.put(newImage);
  //  }

}
