package ru.dankoy.hw16;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw16.core.repository"})
@SpringBootApplication
public class Hw16Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw16Application.class, args);
  }

}
