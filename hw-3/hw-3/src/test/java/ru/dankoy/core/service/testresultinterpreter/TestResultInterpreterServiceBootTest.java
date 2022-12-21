package ru.dankoy.core.service.testresultinterpreter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;


@DisplayName("Test TestResultInterpreter ")
@SpringBootTest
class TestResultInterpreterServiceBootTest {

  @MockBean
  private IOService ioService;
  @Autowired
  private TestResultInterpreterServiceImpl testResultInterpreterService;
  private Student student;

  @Test
  @DisplayName("if student did not pass a test")
  void printResultNotPassedTest() {

    student = new Student("fn", "ln");

    var testResult = new TestResult(3, 2, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' didn't pass test with correct answers - %d",
            student.getFirstAndLastName(), 2));

  }

  @Test
  @DisplayName("if student passed a test")
  void printResultPassedTest() {

    student = new Student("fn", "ln");

    var testResult = new TestResult(3, 5, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' passed test with correct answers - %d", student.getFirstAndLastName(),
            5));

  }

}
