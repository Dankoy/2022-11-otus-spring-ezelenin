package ru.dankoy.hw8;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw8.core.repository"})
@SpringBootApplication
public class Hw8Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw8Application.class, args);
  }

}
