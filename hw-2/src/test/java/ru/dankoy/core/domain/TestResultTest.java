package ru.dankoy.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dankoy.core.service.io.IOService;

class TestResultTest {

  private IOService ioService;
  private Student student;

  @Test
  @DisplayName("Test if student passed test")
  void printResultPassedTest() {

    ioService = Mockito.mock(IOService.class);
    student = new Student("fn", "ln");

    var testResult = new TestResult(ioService, 3, 2, student);

    testResult.printResult();

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' didn't pass test with correct answers - %d",
            student.getFirstAndLastName(),
            2));

  }

  @Test
  @DisplayName("Test if student didn't pass test")
  void printResultNotPassedTest() {

    ioService = Mockito.mock(IOService.class);
    student = new Student("fn", "ln");

    var testResult = new TestResult(ioService, 3, 5, student);

    testResult.printResult();

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' passed test with correct answers - %d", student.getFirstAndLastName(),
            5));

  }


}
