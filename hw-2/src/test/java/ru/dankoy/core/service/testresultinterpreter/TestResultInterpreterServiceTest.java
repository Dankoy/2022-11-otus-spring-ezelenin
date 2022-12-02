package ru.dankoy.core.service.testresultinterpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;

@SpringJUnitConfig
@ActiveProfiles({"test"})
class TestResultInterpreterServiceTest {

  @Autowired
  private IOService ioService;
  @Autowired
  private TestResultInterpreterService testResultInterpreterService;
  private Student student;

  @Test
  @DisplayName("Test if student passed test")
  void printResultPassedTest() {

    student = new Student("fn", "ln");

    var testResult = new TestResult(3, 2, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' didn't pass test with correct answers - %d",
            student.getFirstAndLastName(),
            2));

  }

  @Test
  @DisplayName("Test if student didn't pass test")
  void printResultNotPassedTest() {

    student = new Student("fn", "ln");

    var testResult = new TestResult(3, 5, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' passed test with correct answers - %d", student.getFirstAndLastName(),
            5));

  }

  @PropertySource("classpath:application.properties")
  @Configuration
  @Profile({"test"})
  static class Config {

    @Bean
    TestResultInterpreterService testResultInterpreterService() {
      return new TestResultInterpreterServiceImpl(ioService());
    }

    @Bean
    IOService ioService() {
      return Mockito.mock(IOServiceConsole.class);
    }


  }


}
