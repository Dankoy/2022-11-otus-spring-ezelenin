package ru.dankoy.hw19.config.mongodbaudit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class MongodbAuditionConfig {

  @Bean
  public AuditorAware<String> myAuditorProvider() {
    return new AuditorAwareImpl();
  }

}
