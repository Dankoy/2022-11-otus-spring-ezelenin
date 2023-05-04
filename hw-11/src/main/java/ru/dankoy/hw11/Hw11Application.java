package ru.dankoy.hw11;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw11.core.repository"})
@SpringBootApplication
public class Hw11Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw11Application.class, args);
  }

}
