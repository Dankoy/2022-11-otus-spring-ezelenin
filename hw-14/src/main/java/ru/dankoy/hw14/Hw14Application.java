package ru.dankoy.hw14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableJpaRepositories(basePackages = "ru.dankoy.hw14.core.repository.sql")
@EnableMongoRepositories(basePackages = "ru.dankoy.hw14.core.repository.mongodb")
@SpringBootApplication
public class Hw14Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw14Application.class, args);
  }

}
