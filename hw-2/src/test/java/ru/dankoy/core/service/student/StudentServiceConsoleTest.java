package ru.dankoy.core.service.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;


@SpringJUnitConfig()
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@ActiveProfiles({"test"})
class StudentServiceConsoleTest {

  private static final String studentFirstAndLastName = "abc";
  @Autowired
  private StudentServiceConsole studentService;

  @Autowired
  private IOService ioService;

  @Test
  @DisplayName("Test correct creation of student object")
  void getStudentTest() {

    var correctStudent = new Student(studentFirstAndLastName, studentFirstAndLastName);

    Mockito.when(ioService.readLn()).thenReturn(studentFirstAndLastName);

    var student = studentService.getStudent();

    Mockito.verify(ioService, Mockito.times(2)).readLn();

    assertEquals(student, correctStudent);

  }

  @PropertySource("classpath:application.properties")
  @Configuration
  @Profile({"test"})
  static class Config {

    @Bean
    public StudentServiceConsole studentService() {
      return new StudentServiceConsole(ioService());
    }

    @Bean
    public IOService ioService() {
      return Mockito.mock(IOServiceConsole.class);
    }
  }

}
