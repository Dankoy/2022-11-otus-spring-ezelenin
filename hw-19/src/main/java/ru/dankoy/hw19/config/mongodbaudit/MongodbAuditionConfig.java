package ru.dankoy.hw19.config.mongodbaudit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import ru.dankoy.hw19.core.domain.User;

@EnableMongoAuditing
@Configuration
public class MongodbAuditionConfig {

  @Bean
  public AuditorAware<User> myAuditorProvider() {
    return new AuditorAwareImpl();
  }

}
