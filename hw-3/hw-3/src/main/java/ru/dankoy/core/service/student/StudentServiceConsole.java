package ru.dankoy.core.service.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.service.io.IOService;

/**
 * @author turtality
 * <p>
 * Student service that gets student data from console
 */
@Service
public class StudentServiceConsole implements StudentService {

  private final IOService ioService;

  private final String askStudentName;

  private final String askFirstName;

  private final String askLastName;

  public StudentServiceConsole(IOService ioService, @Value("${hw3.askStudentName}") String askStudentName,
      @Value("${hw3.firstName}") String askFirstName,
      @Value("${hw3.lastName}") String askLastName) {
    this.ioService = ioService;
    this.askFirstName = askFirstName;
    this.askStudentName = askStudentName;
    this.askLastName = askLastName;
  }


  /**
   * Asks in console student data
   *
   * @return student {@link Student}
   */
  @Override
  public Student getStudent() {

    ioService.print(askStudentName);
    ioService.print(askFirstName);
    String firstName = ioService.readLn();

    ioService.print(askLastName);
    String lastName = ioService.readLn();

    return new Student(firstName, lastName);

  }
}
