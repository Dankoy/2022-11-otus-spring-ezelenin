package ru.dankoy.hw19;

import com.github.cloudyrock.spring.v5.EnableMongock;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories(basePackages = {"ru.dankoy.hw19.core.repository"})
@EnableEncryptableProperties
@SpringBootApplication
public class Hw19Application {

  public static void main(String[] args) {
    SpringApplication.run(Hw19Application.class, args);
  }

}
