package ru.dankoy.core.service.printer;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.dankoy.config.AppProperties;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.service.io.IOService;

@Component
public class PrinterQuestionsImpl implements Printer {

  private static final String SEPARATOR = "line.separator";
  private final IOService ioService;
  private final AppProperties appProperties;
  private final MessageSource messageSource;

  public PrinterQuestionsImpl(IOService ioService, AppProperties appProperties,
      MessageSource messageSource) {
    this.ioService = ioService;
    this.appProperties = appProperties;
    this.messageSource = messageSource;
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
        String.format(messageSource.getMessage("questionTemplate", null, appProperties.getLocale()),
            question.getId(), question.getQuestionText()));
    stringBuilder.append(System.getProperty(SEPARATOR));

    for (Answer answer : question.getAnswers()) {
      stringBuilder.append(
          String.format(messageSource.getMessage("answerTemplate", null, appProperties.getLocale()),
              answer.getId(), answer.getAnswerText()));
      stringBuilder.append(System.getProperty(SEPARATOR));
    }

    stringBuilder.append(System.getProperty(SEPARATOR));

    ioService.print(stringBuilder.toString());

  }

}
