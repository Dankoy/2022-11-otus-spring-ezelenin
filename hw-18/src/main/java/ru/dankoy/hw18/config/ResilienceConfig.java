package ru.dankoy.hw18.config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResilienceConfig {

  @Bean
  public CircuitBreakerRegistry circuitBreakerRegistry() {

    return CircuitBreakerRegistry.ofDefaults();
  }

  @Bean
  public TimeLimiterRegistry timeLimiterRegistry() {
    return TimeLimiterRegistry.ofDefaults();
  }


}
