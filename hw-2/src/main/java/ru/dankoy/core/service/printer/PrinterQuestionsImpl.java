package ru.dankoy.core.service.printer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.service.io.IOService;

@Component
public class PrinterQuestionsImpl implements Printer {

  private static final String SEPARATOR = "line.separator";
  private final IOService ioService;
  private String questionTemplate;
  private String answerTemplate;

  public PrinterQuestionsImpl(IOService ioService,
      @Value("${questionTemplate}") String questionTemplate,
      @Value("${answerTemplate}") String answerTemplate) {
    this.ioService = ioService;
    this.questionTemplate = questionTemplate;
    this.answerTemplate = answerTemplate;
  }

  /**
   * Prints question with it's possible answers
   *
   * @param question question to print
   */
  @Override
  public void printQuestion(Question question) {

    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder.append(
        String.format(questionTemplate, question.getId(), question.getQuestionText()));
    stringBuilder.append(System.getProperty(SEPARATOR));

    for (Answer answer : question.getAnswers()) {
      stringBuilder.append(String.format(answerTemplate, answer.getId(), answer.getAnswerText()));
      stringBuilder.append(System.getProperty(SEPARATOR));
    }

    stringBuilder.append(System.getProperty(SEPARATOR));

    ioService.print(stringBuilder.toString());

  }

}
