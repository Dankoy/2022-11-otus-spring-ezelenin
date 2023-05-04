package ru.dankoy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;

@Configuration
public class AppConfig {

  @Bean
  public IOService ioService() {
    return new IOServiceConsole(System.out, System.in);
  }

}
