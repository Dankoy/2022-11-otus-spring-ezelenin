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

  @Value("${askStudentName}")
  private String askStudentName;
  @Value("${firstName}")
  private String askFirstName;
  @Value("${lastName}")
  private String askLastName;

  private final IOService ioService;

  public StudentServiceConsole(IOService ioService) {
    this.ioService = ioService;
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
