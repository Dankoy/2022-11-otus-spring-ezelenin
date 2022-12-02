package ru.dankoy.core.domain;

/**
 * @author Dankoy
 * <p>
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Student)) {
      return false;
    }

    Student student = (Student) o;

    if (!firstName.equals(student.firstName)) {
      return false;
    }
    return lastName.equals(student.lastName);
  }

  @Override
  public int hashCode() {
    int result = firstName.hashCode();
    result = 31 * result + lastName.hashCode();
    return result;
  }
}
