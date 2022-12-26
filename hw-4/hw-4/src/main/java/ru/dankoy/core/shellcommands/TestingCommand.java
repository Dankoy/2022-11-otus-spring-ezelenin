package ru.dankoy.core.shellcommands;


import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.dankoy.core.service.test.TestingPerformer;
import ru.dankoy.core.service.testresultinterpreter.TestResultInterpreterService;

/**
 * @author turtality
 * <p>
 * Shell command for test running
 */
@AllArgsConstructor
@ShellComponent
public class TestingCommand {

  private final TestingPerformer testingPerformer;
  private final TestResultInterpreterService testResultInterpreterService;

  @ShellMethod(key = {"test", "t"}, value = "Perform testing")
  public void performTest() {

    var testResult = testingPerformer.performTest();

    testResultInterpreterService.interpretTestResult(testResult);

  }

}
