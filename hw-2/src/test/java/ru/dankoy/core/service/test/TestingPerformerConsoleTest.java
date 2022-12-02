package ru.dankoy.core.service.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;
import ru.dankoy.core.service.printer.Printer;
import ru.dankoy.core.service.printer.PrinterQuestionsImpl;
import ru.dankoy.core.service.questions.QuestionsService;
import ru.dankoy.core.service.questions.QuestionsServiceImpl;
import ru.dankoy.core.service.student.StudentService;
import ru.dankoy.core.service.student.StudentServiceConsole;

@SpringJUnitConfig
@ContextConfiguration(loader= AnnotationConfigContextLoader.class)
@ActiveProfiles({ "test" })
class TestingPerformerConsoleTest {


  @PropertySource("classpath:application.properties")
  @Configuration
  @Profile({ "test" })
  static class Config {

    @Value("${amountOfCorrectAnswersToPassTest}")
    int amountOfCorrectAnswersToPassTest;

    @Bean
    TestingPerformer testingPerformer() {
      return new TestingPerformerConsole(ioService(), studentService(), questionsService(),
          printer(),
          amountOfCorrectAnswersToPassTest);
    }

    @Bean
    IOService ioService() {
      return Mockito.mock(IOServiceConsole.class);
    }

    @Bean
    StudentService studentService() {
      return Mockito.mock(StudentServiceConsole.class);
    }

    @Bean
    QuestionsService questionsService() {
      return Mockito.mock(QuestionsServiceImpl.class);
    }

    @Bean
    Printer printer() {
      return Mockito.mock(PrinterQuestionsImpl.class);
    }

  }

  @Autowired
  private QuestionsService questionsService;

  @Autowired
  private StudentService studentService;

  @Autowired
  private IOService ioService;

  @Autowired
  private TestingPerformer testingPerformer;

  private static final String fn = "abc";
  private static final String ln = "cba";

  private static Student student;

  @Test
  @DisplayName("Testing if algorithm of testing works correctly")
  void performTestTest() {

    student = new Student(fn, ln);

    Mockito.when(ioService.readLong())
        .thenReturn(1L, 2L, 3L, 4L, 5L);
    Mockito.when(questionsService.getQuestions()).thenReturn(makeQuestions());
    Mockito.when(studentService.getStudent()).thenReturn(student);

    TestResult testResult = testingPerformer.performTest();

    assertEquals(testResult, makeTestResult());

    Mockito.verify(ioService, Mockito.times(5)).readLong();
    Mockito.verify(questionsService, Mockito.times(1)).getQuestions();
    Mockito.verify(studentService, Mockito.times(1)).getStudent();

  }


  private TestResult makeTestResult() {

    return new TestResult(ioService, 3, 2, student);

  }

  private List<Question> makeQuestions() {

    String[] questionTexts = new String[]{
        "q1",
        "q2",
        "q3",
        "q4",
        "q5"
    };

    long[] correctAnswers = new long[]{
        1L, 2L, 4L, 2L, 3L
    };

    List<Question> questions = new ArrayList<>();
    for (int i = 0; i < questionTexts.length; i++) {

      List<Answer> answers = List.of(
          new Answer(1, "a1"),
          new Answer(2, "a2"),
          new Answer(3, "a3"),
          new Answer(4, "a4")
      );

      var question = new Question(1, questionTexts[i], answers, correctAnswers[i]);

      questions.add(question);

    }

    return questions;

  }


}
