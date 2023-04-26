package com.example.restApiExample.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException() {
    super("Specified person not found!");
  }
}
