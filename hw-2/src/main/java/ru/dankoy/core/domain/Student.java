package ru.dankoy.core.domain;

/**
 * @author Dankoy
 *
 * Testing student
 */
public class Student {

  private final String firstName;
  private final String lastName;

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstAndLastName() {
    return String.format("%s %s", firstName, lastName);
  }

}
