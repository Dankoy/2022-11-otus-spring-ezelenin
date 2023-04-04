package ru.dankoy.hw10;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw10.core.repository"})
@SpringBootApplication
public class Hw10Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw10Application.class, args);
  }

}
