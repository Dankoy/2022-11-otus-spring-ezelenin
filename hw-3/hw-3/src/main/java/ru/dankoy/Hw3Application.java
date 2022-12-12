package ru.dankoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@PropertySource("classpath:application.properties")
@SpringBootApplication
public class Hw3Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw3Application.class, args);
  }

}