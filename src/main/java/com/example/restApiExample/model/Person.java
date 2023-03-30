package com.example.restApiExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

  private final int id;
  private String firstName;
  private String lastName;
  private int age;
  private Company workplace;
}
