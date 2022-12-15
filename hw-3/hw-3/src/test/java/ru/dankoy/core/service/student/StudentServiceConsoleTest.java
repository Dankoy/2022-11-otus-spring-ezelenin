package ru.dankoy.core.service.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dankoy.config.LocaleProvider;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.service.io.IOService;


@SpringJUnitConfig()
class StudentServiceConsoleTest {

  private static final String studentFirstAndLastName = "abc";
  private final Locale locale = new Locale("en_US");
  @InjectMocks
  private StudentServiceConsole studentService;
  @Mock
  private IOService ioService;
  @Mock
  private LocaleProvider localeProvider;
  @Mock
  private MessageSource messageSource;

  @Test
  @DisplayName("Test correct creation of student object")
  void getStudentTest() {

    given(localeProvider.getLocale()).willReturn(locale);
    given(messageSource.getMessage(any(), any(), eq(locale))).willReturn(
        studentFirstAndLastName);
    given(ioService.readLn()).willReturn(studentFirstAndLastName);
    willDoNothing().given(ioService).print(any());

    var correctStudent = new Student(studentFirstAndLastName, studentFirstAndLastName);

    var student = studentService.getStudent();

    Mockito.verify(ioService, Mockito.times(2)).readLn();

    assertEquals(student, correctStudent);

  }


}
