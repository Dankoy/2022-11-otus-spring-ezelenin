package ru.dankoy.hw12.core.controller;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.dankoy.hw12.core.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ErrorAdvice {

  @ExceptionHandler(value = {EntityNotFoundException.class})
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  protected ModelAndView handleNotFound(RuntimeException ex, WebRequest request) {

    Map<String, Object> model = new HashMap<>();
    model.put("errorMessage", ex.getMessage());
    return new ModelAndView("404", model);


  }


}
