package ru.dankoy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.dankoy.core.service.test.TestingPerformerConsole;
import ru.dankoy.core.service.testresultinterpreter.TestResultInterpreterServiceImpl;


@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {

  public static void main(String[] args) {

    var ctx = new AnnotationConfigApplicationContext(Main.class);
    var testPerformer = ctx.getBean(TestingPerformerConsole.class);
    var testResultInterpreter = ctx.getBean(TestResultInterpreterServiceImpl.class);
    var testResult = testPerformer.performTest();
    testResultInterpreter.interpretTestResult(testResult);

  }
}