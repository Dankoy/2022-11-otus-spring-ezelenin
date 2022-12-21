package ru.dankoy.core.service.printer;

import org.springframework.stereotype.Component;
import ru.dankoy.config.PrinterPropertiesProvider;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.service.io.IOService;

@Component
public class QuestionPrinterImpl implements QuestionPrinter {

  private static final String SEPARATOR = "line.separator";
  private final IOService ioService;
  private final PrinterPropertiesProvider appProperties;

  public QuestionPrinterImpl(IOService ioService, PrinterPropertiesProvider appProperties) {
    this.ioService = ioService;
    this.appProperties = appProperties;
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
        String.format(appProperties.getQuestionTemplate(), question.getId(),
            question.getQuestionText()));
    stringBuilder.append(System.getProperty(SEPARATOR));

    for (Answer answer : question.getAnswers()) {
      stringBuilder.append(
          String.format(appProperties.getAnswerTemplate(), answer.getId(), answer.getAnswerText()));
      stringBuilder.append(System.getProperty(SEPARATOR));
    }

    stringBuilder.append(System.getProperty(SEPARATOR));

    ioService.print(stringBuilder.toString());

  }

}
