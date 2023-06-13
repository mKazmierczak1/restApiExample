package com.example.rsiMiniProject.service;

import com.example.rsiMiniProject.dao.ImageDao;
import com.example.rsiMiniProject.exeptions.ImageNotFoundException;
import com.example.rsiMiniProject.model.Image;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageDao imageDao;

  public Image getById(int id) throws ImageNotFoundException {
    return imageDao.getById(id);
  }

  public List<Image> getAll() {
    return imageDao.getAll();
  }

  public Image put(Image newImage) {
    return imageDao.put(newImage);
  }

  public Image update(int id, Image newImage) {
    return imageDao.update(id, newImage);
  }

  public Image delete(int id) {
    return imageDao.delete(id);
  }
}
