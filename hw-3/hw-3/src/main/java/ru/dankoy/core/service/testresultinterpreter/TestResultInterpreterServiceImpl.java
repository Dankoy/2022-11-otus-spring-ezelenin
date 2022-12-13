package ru.dankoy.core.service.testresultinterpreter;


import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.dankoy.config.AppProperties;
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
  private final MessageSource messageSource;
  private final AppProperties appProperties;

  public TestResultInterpreterServiceImpl(IOService ioService, MessageSource messageSource,
      AppProperties appProperties) {
    this.ioService = ioService;
    this.messageSource = messageSource;
    this.appProperties = appProperties;
  }

  /**
   * Prints results if student passed test or not
   */
  @Override
  public void interpretTestResult(TestResult testResult) {

    if (testResult.getCorrectAnsweredAmount() >= testResult.getAmountOfCorrectAnswersToPassTest()) {
      ioService.print(String.format(
          messageSource.getMessage("testPassed", null, appProperties.getLocale()),
          testResult.getStudent().getFirstAndLastName(),
          testResult.getCorrectAnsweredAmount()));
    } else {
      ioService.print(
          String.format(messageSource.getMessage("testNotPassed", null, appProperties.getLocale()),
              testResult.getStudent().getFirstAndLastName(),
              testResult.getCorrectAnsweredAmount()));
    }


  }
}
