package ru.dankoy.core.service.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ru.dankoy.config.AppProperties;
import ru.dankoy.config.TestEvaluationProvider;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.io.IOServiceConsole;
import ru.dankoy.core.service.printer.QuestionPrinter;
import ru.dankoy.core.service.questions.QuestionsService;
import ru.dankoy.core.service.questions.QuestionsServiceImpl;
import ru.dankoy.core.service.student.StudentService;
import ru.dankoy.core.service.student.StudentServiceConsole;

@DisplayName("Test TestingPerformer ")
@SpringBootTest
class TestingPerformerConsoleTest {

  private static final String fn = "abc";
  private static final String ln = "cba";
  private static Student student;
  private final int amountOfCorrectAnswersToPassTest = 3;
  @MockBean
  private QuestionsService questionsService;
  @MockBean
  private StudentService studentService;
  @MockBean
  private IOService ioService;
  @MockBean
  private TestEvaluationProvider testEvaluationProvider;
  @MockBean
  private QuestionPrinter questionPrinter;
  @Autowired
  private TestingPerformerConsole testingPerformer;

  @Test
  @DisplayName("if algorithm of testing works correctly, returns correct TestResult object")
  void shouldReturnCorrectTestResult() {

    student = new Student(fn, ln);

    given(ioService.readLong()).willReturn(1L, 2L, 3L, 4L, 5L);
    given(questionsService.getQuestions()).willReturn(makeQuestions());
    given(studentService.getStudent()).willReturn(student);
    given(testEvaluationProvider.getAmountOfCorrectAnswersToPassTest()).willReturn(
        amountOfCorrectAnswersToPassTest);

    TestResult testResult = testingPerformer.performTest();

    assertEquals(testResult, makeTestResult());

    Mockito.verify(ioService, Mockito.times(5)).readLong();
    Mockito.verify(questionsService, Mockito.times(1)).getQuestions();
    Mockito.verify(studentService, Mockito.times(1)).getStudent();
    Mockito.verify(questionPrinter, Mockito.times(5)).printQuestion(any(Question.class));

  }

  private TestResult makeTestResult() {

    return new TestResult(amountOfCorrectAnswersToPassTest, 2, student);

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

  @PropertySource("classpath:application.yml")
  @Configuration
  @Import({QuestionsServiceImpl.class, StudentServiceConsole.class, IOServiceConsole.class,
      AppProperties.class, TestingPerformerConsole.class})
  static class Config {

  }


}
