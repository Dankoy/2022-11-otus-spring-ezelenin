package ru.dankoy.hw10.core.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.dankoy.hw10.core.exceptions.EntityNotFoundException;

@RestControllerAdvice
public class ErrorAdvice {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(ex.getMessage());

  }


}
