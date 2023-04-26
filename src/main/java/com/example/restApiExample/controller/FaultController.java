package com.example.restApiExample.controller;

import com.example.restApiExample.exeptions.PersonNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {

  @ResponseBody
  @ExceptionHandler(PersonNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  ResponseEntity<?> PersonNotFoundExceptionHandler(PersonNotFoundException exception) {
    var problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problem.setDetail(exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
        .body(problem);
  }
}
