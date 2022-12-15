package ru.dankoy.core.service.testresultinterpreter;

import static org.mockito.BDDMockito.given;

import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.dankoy.config.AppProperties;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;


@DisplayName("Test testing interpretation logic")
@ExtendWith(MockitoExtension.class)
class TestResultInterpreterServiceTest {

  @Mock
  private IOService ioService;

  @Mock
  private MessageSource messageSource;

  @Mock
  private AppProperties appProperties;
  @InjectMocks
  private TestResultInterpreterServiceImpl testResultInterpreterService;
  private Student student;

  private final Locale locale = new Locale("en_US");

  @Test
  @DisplayName("if student did not pass a test")
  void printResultNotPassedTest() {

    student = new Student("fn", "ln");

    given(appProperties.getLocale()).willReturn(locale);
    given(messageSource.getMessage("testNotPassed", null, locale)).willReturn(
        "Student '%s' didn't pass test with correct answers - %d");

    var testResult = new TestResult(3, 2, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' didn't pass test with correct answers - %d",
            student.getFirstAndLastName(), 2));
    Mockito.verify(appProperties, Mockito.times(1)).getLocale();

  }

  @Test
  @DisplayName("if student passed a test")
  void printResultPassedTest() {

    given(appProperties.getLocale()).willReturn(locale);
    given(messageSource.getMessage("testPassed", null, locale)).willReturn(
        "Student '%s' passed test with correct answers - %d");

    student = new Student("fn", "ln");

    var testResult = new TestResult(3, 5, student);

    testResultInterpreterService.interpretTestResult(testResult);

    Mockito.verify(ioService, Mockito.times(1))
        .print(String.format(
            "Student '%s' passed test with correct answers - %d", student.getFirstAndLastName(),
            5));
    Mockito.verify(appProperties, Mockito.times(1)).getLocale();

  }

  /*
  * для тестирования со спрингом:
  *
  *   @Configuration
  @Profile({"test"})
  @Import({TestResultInterpreterServiceImpl.class, IOServiceConsole.class,
      ResourceBundleMessageSource.class, AppConfigProperties.class})
  static class Config {

    // Пришлось переопределить бин, что бы в тестах были видны данные ресурсы. Если этого не сделать,
    // то возвращается либо пустой messagesource, либо немокнутый, методы которого невозможно переопределить.
    // @TestPropertySource(locations = {"classpath:i18n/appmessages.properties"}) - так же не работает,
    // даже при наличии
    @Bean
    public ResourceBundleMessageSource messageSource() {
      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
      source.setBasenames("i18n/appmessages");
      return source;
    }
  }
  *
  * */


}
