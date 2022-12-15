package ru.dankoy.core.service.testresultinterpreter;


import org.springframework.stereotype.Service;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;

/**
 * @author turtality
 * <p>
 * Service that prints results of test
 */
@Service
public class TestResultInterpreterServiceImpl implements TestResultInterpreterService {

  private final IOService ioService;

  public TestResultInterpreterServiceImpl(IOService ioService) {
    this.ioService = ioService;
  }

  /**
   * Prints results if student passed test or not
   */
  @Override
  public void interpretTestResult(TestResult testResult) {

    if (testResult.getCorrectAnsweredAmount() >= testResult.getAmountOfCorrectAnswersToPassTest()) {
      ioService.print(String.format(
          "Student '%s' passed test with correct answers - %d",
          testResult.getStudent().getFirstAndLastName(),
          testResult.getCorrectAnsweredAmount()));
    } else {
      ioService.print(
          String.format("Student '%s' didn't pass test with correct answers - %d",
              testResult.getStudent().getFirstAndLastName(),
              testResult.getCorrectAnsweredAmount()));
    }


  }
}
