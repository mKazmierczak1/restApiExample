package com.example.rsiMiniProject.dao;

import com.example.rsiMiniProject.exeptions.ImageNotFoundException;
import com.example.rsiMiniProject.model.Image;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ImageDao {

  private final List<Image> people;

  public ImageDao() {
    people = new ArrayList<>();

    for (int i = 1; i < 10; i++) {
      people.add(new Image(i, "firstName" + i, "lastName" + i, i + 20));
    }
  }

  public Image getById(int id) throws ImageNotFoundException {
    return people.stream()
        .filter(image -> image.getId() == id)
        .findFirst()
        .orElseThrow(ImageNotFoundException::new);
  }

  public List<Image> getAll() {
    return people;
  }

  public Image put(Image newImage) {
    people.add(newImage);
    return newImage;
  }

  public Image update(int id, Image newImage) throws ImageNotFoundException {
    var personToUpdate = getById(id);

    personToUpdate.setFirstName(newImage.getFirstName());
    personToUpdate.setLastName(newImage.getLastName());
    personToUpdate.setAge(newImage.getAge());

    return personToUpdate;
  }

  public Image delete(int id) throws ImageNotFoundException {
    var personToDelete = getById(id);
    people.remove(personToDelete);
    return personToDelete;
  }
}
