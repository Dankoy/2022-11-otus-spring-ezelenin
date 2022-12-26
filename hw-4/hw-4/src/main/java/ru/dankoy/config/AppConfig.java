package ru.dankoy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;

/**
 * @author turtality
 * <p>
 * Configuration for beans that couldn't be created with annotations
 */
@Configuration
public class AppConfig {

  @Bean
  public IOService ioService() {
    return new IOServiceConsole(System.out, System.in);
  }

}
