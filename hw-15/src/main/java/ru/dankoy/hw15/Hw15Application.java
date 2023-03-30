package ru.dankoy.hw15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.dankoy.hw15.core.service.LifecycleService;

@SpringBootApplication
public class Hw15Application {

  public static void main(String[] args) {
//    SpringApplication.run(Hw15Application.class, args);

    ConfigurableApplicationContext ctx = SpringApplication.run(Hw15Application.class, args);
    var lifecycleService = ctx.getBean(LifecycleService.class);
    lifecycleService.runXenoProcess();
  }

}
