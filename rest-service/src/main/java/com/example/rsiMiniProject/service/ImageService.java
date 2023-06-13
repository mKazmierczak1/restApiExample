package com.example.rsiMiniProject.service;

import com.example.rsiMiniProject.exeptions.ImageNotFoundException;
import com.example.rsiMiniProject.rabbit.ImageRpcHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRpcHandler imageRpcHandler;

  public byte[] getImageById(int id) throws ImageNotFoundException {
    return imageRpcHandler.getImage("jpg", id);
  }

  public byte[] getGifById(int id) throws ImageNotFoundException {
    return imageRpcHandler.getImage("gif", id);
  }

  //  public Image put(Image newImage) {
  //    return imageDao.put(newImage);
  //  }
}
