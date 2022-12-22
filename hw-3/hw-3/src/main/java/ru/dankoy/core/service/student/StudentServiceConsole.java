package ru.dankoy.core.service.student;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.dankoy.config.LocaleProvider;
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
  private final LocaleProvider appProperties;
  private final MessageSource messageSource;

  public StudentServiceConsole(IOService ioService, LocaleProvider appProperties,
      MessageSource messageSource) {
    this.ioService = ioService;
    this.appProperties = appProperties;
    this.messageSource = messageSource;
  }


  /**
   * Asks in console student data
   *
   * @return student {@link Student}
   */
  @Override
  public Student getStudent() {

    ioService.print(messageSource.getMessage("askStudentName", null, appProperties.getLocale()));
    ioService.print(messageSource.getMessage("firstName", null, appProperties.getLocale()));
    String firstName = ioService.readLn();

    ioService.print(messageSource.getMessage("lastName", null, appProperties.getLocale()));
    String lastName = ioService.readLn();

    return new Student(firstName, lastName);

  }
}
