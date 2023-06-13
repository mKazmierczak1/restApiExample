package com.example.rsiMiniProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Image {

  private final int id;
  private String firstName;
  private String lastName;
  private int age;
}
