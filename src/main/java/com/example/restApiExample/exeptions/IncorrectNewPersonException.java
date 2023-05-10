package com.example.restApiExample.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class IncorrectNewPersonException extends RuntimeException {

  public IncorrectNewPersonException() {
    super("New person contains incorrect data!");
  }
}
