package com.example.rsiMiniProject.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.rsiMiniProject.exeptions.ImageNotFoundException;
import com.example.rsiMiniProject.exeptions.IncorrectNewImageException;
import com.example.rsiMiniProject.model.Image;
import com.example.rsiMiniProject.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ImageController {

  private final ImageService imageService;

  @GetMapping("/image")
  CollectionModel<Image> getAll() {
    return CollectionModel.of(
        imageService.getAll(), linkTo(methodOn(ImageController.class).getAll()).withSelfRel());
  }

  @GetMapping("/image/{id}")
  public EntityModel<Image> getById(@PathVariable Integer id) throws ImageNotFoundException {
    return EntityModel.of(
        imageService.getById(id),
        linkTo(methodOn(ImageController.class).getById(id)).withSelfRel(),
        linkTo(methodOn(ImageController.class).deleteImage(id)).withRel("delete"),
        linkTo(methodOn(ImageController.class).getAll()).withRel("list all"));
  }

  @PostMapping("/image")
  Image newImage(@RequestBody Image newImage) {
    if (newImage.getFirstName().isEmpty() || newImage.getLastName().isEmpty()) {
      throw new IncorrectNewImageException();
    }

    return imageService.put(newImage);
  }

  @PutMapping("/image/{id}")
  Image updateImage(@PathVariable Integer id, @RequestBody Image newImage) {
    return imageService.update(id, newImage);
  }

  @DeleteMapping("/image/{id}")
  Image deleteImage(@PathVariable Integer id) {
    return imageService.delete(id);
  }
}
