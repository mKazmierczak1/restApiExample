package com.example.restApiExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Company {

  private final int id;
  private String name;
  private String city;
  private String street;
  private String buildingNumber;
}
