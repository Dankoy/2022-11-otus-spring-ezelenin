package ru.dankoy.core.service.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.service.io.IOService;


@DisplayName("Test Student Service ")
@SpringBootTest
class StudentServiceConsoleTest {

  private static final String studentFirstAndLastName = "abc";
  @Autowired
  private StudentServiceConsole studentService;
  @MockBean
  private IOService ioService;

  @Test
  @DisplayName("correct creation of student object")
  void shouldReturnCorrectStudentStudent() {

    given(ioService.readLn()).willReturn(studentFirstAndLastName);
    willDoNothing().given(ioService).print(any());

    var correctStudent = new Student(studentFirstAndLastName, studentFirstAndLastName);

    var student = studentService.getStudent();

    Mockito.verify(ioService, Mockito.times(2)).readLn();

    assertEquals(student, correctStudent);

  }


}
