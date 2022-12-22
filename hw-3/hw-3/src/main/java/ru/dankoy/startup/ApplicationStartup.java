package ru.dankoy.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.dankoy.core.service.test.TestingPerformerConsole;
import ru.dankoy.core.service.testresultinterpreter.TestResultInterpreterServiceImpl;

/**
 * @author turtality
 * <p>
 * Moved application runner from Main to here. Contains method which runs after spring context
 * creation is completed.
 */
@Component
public class ApplicationStartup {

  private final ApplicationContext context;

  @Autowired
  public ApplicationStartup(ApplicationContext context) {
    this.context = context;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void run() {
    var testPerformer = context.getBean(TestingPerformerConsole.class);
    var testResultInterpreter = context.getBean(TestResultInterpreterServiceImpl.class);
    var testResult = testPerformer.performTest();
    testResultInterpreter.interpretTestResult(testResult);
  }
}
