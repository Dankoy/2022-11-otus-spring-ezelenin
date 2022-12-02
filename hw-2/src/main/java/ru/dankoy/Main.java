package ru.dankoy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.dankoy.core.service.test.TestingPerformerConsole;


@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {

  public static void main(String[] args) {

    var ctx = new AnnotationConfigApplicationContext(Main.class);
    var testPerformer = ctx.getBean(TestingPerformerConsole.class);
    var testResult = testPerformer.performTest();
    testResult.printResult();

  }
}