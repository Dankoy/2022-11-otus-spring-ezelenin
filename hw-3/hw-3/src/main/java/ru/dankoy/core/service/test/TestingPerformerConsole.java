package ru.dankoy.core.service.test;

import org.springframework.stereotype.Service;
import ru.dankoy.config.TestEvaluationProvider;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.printer.QuestionPrinter;
import ru.dankoy.core.service.questions.QuestionsService;
import ru.dankoy.core.service.student.StudentService;

/**
 * @author Dankoy
 * <p>
 * Actual testing runs here
 */
@Service
public class TestingPerformerConsole implements TestingPerformer {

  private final QuestionPrinter questionPrinter;
  private final IOService ioService;
  private final StudentService studentService;
  private final QuestionsService questionsService;
  private final TestEvaluationProvider appProperties;


  public TestingPerformerConsole(IOService ioService, StudentService studentService,
      QuestionsService questionsService, QuestionPrinter questionPrinter,
      TestEvaluationProvider appProperties) {
    this.ioService = ioService;
    this.studentService = studentService;
    this.questionsService = questionsService;
    this.questionPrinter = questionPrinter;
    this.appProperties = appProperties;
  }


  /**
   * Testing. Get questions and student from {@link QuestionsService} and {@link StudentService}
   *
   * @return test result {@link TestResult}
   */
  @Override
  public TestResult performTest() {

    // получаем вопросы
    var questions = questionsService.getQuestions();

    // получаем фи студента
    var student = studentService.getStudent();

    var correctAnsweredAmount = 0;
    // проводим опрос и получаем количество корректных ответов
    for (Question question : questions) {

      questionPrinter.printQuestion(question);
      long answer = ioService.readLong();

      if (answer == question.getCorrectAnswerId()) {
        correctAnsweredAmount++;
      }

    }

    return new TestResult(appProperties.getAmountOfCorrectAnswersToPassTest(),
        correctAnsweredAmount, student);

  }
}