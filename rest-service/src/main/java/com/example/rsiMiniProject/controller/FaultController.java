package com.example.rsiMiniProject.controller;

import com.example.rsiMiniProject.exceptions.ImageNotFoundException;
import com.example.rsiMiniProject.exceptions.IncorrectNewImageException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {

  @ResponseBody
  @ExceptionHandler(ImageNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  ResponseEntity<?> ImageNotFoundExceptionHandler(ImageNotFoundException exception) {
    var problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
    problem.setDetail(exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
        .body(problem);
  }

  @ResponseBody
  @ExceptionHandler(IncorrectNewImageException.class)
  @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
  ResponseEntity<?> IncorrectNewImageExceptionHandler(IncorrectNewImageException exception) {
    var problem = ProblemDetail.forStatus(HttpStatus.NOT_ACCEPTABLE);
    problem.setDetail(exception.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
        .body(problem);
  }
}
