package ru.dankoy.core.domain;

/**
 * @author turtality
 * <p>
 * Consists of test results for student
 */
public class TestResult {

  private final int amountOfCorrectAnswersToPassTest;
  private final int correctAnsweredAmount;
  private final Student student;

  public TestResult(int amountOfCorrectAnswersToPassTest,
      int correctAnsweredAmount,
      Student student) {
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
    this.student = student;
    this.correctAnsweredAmount = correctAnsweredAmount;
  }

  public int getAmountOfCorrectAnswersToPassTest() {
    return amountOfCorrectAnswersToPassTest;
  }

  public int getCorrectAnsweredAmount() {
    return correctAnsweredAmount;
  }

  public Student getStudent() {
    return student;
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
