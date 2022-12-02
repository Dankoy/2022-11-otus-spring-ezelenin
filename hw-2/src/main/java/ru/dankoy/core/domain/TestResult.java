package ru.dankoy.core.domain;

import ru.dankoy.core.service.io.IOService;

/**
 * @author turtality
 * <p>
 * Consists of test results for student
 */
public class TestResult {

  private final int amountOfCorrectAnswersToPassTest;
  private final int correctAnsweredAmount;
  private final Student student;
  private final IOService ioService;

  public TestResult(IOService ioService,
      int amountOfCorrectAnswersToPassTest,
      int correctAnsweredAmount,
      Student student) {
    this.ioService = ioService;
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
    this.student = student;
    this.correctAnsweredAmount = correctAnsweredAmount;
  }

  /**
   * Prints results if student passed test or not
   */
  public void printResult() {

    if (correctAnsweredAmount >= amountOfCorrectAnswersToPassTest) {
      ioService.print(String.format(
          "Student '%s' passed test with correct answers - %d", student.getFirstAndLastName(),
          correctAnsweredAmount));
    } else {
      ioService.print(
          String.format("Student '%s' didn't pass test with correct answers - %d",
              student.getFirstAndLastName(),
              correctAnsweredAmount));
    }

  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TestResult)) {
      return false;
    }

    TestResult that = (TestResult) o;

    if (amountOfCorrectAnswersToPassTest != that.amountOfCorrectAnswersToPassTest) {
      return false;
    }
    if (correctAnsweredAmount != that.correctAnsweredAmount) {
      return false;
    }
    return student.equals(that.student);
  }

  @Override
  public int hashCode() {
    int result = amountOfCorrectAnswersToPassTest;
    result = 31 * result + correctAnsweredAmount;
    result = 31 * result + student.hashCode();
    return result;
  }
}
