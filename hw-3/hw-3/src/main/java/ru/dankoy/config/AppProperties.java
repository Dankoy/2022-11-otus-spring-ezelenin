package ru.dankoy.config;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author turtality
 * <p>
 * Aggregates certain properties from yml file.
 */
@ConfigurationProperties(prefix = "hw3")
public class AppProperties implements LocaleProvider, PrinterPropertiesProvider,
    TestEvaluationProvider {

  private String answerTemplate;
  private String questionTemplate;
  private int amountOfCorrectAnswersToPassTest;
  private Locale locale;

  @Override
  public String getAnswerTemplate() {
    return answerTemplate;
  }

  public void setAnswerTemplate(String answerTemplate) {
    this.answerTemplate = answerTemplate;
  }

  @Override
  public String getQuestionTemplate() {
    return questionTemplate;
  }

  public void setQuestionTemplate(String questionTemplate) {
    this.questionTemplate = questionTemplate;
  }

  @Override
  public int getAmountOfCorrectAnswersToPassTest() {
    return amountOfCorrectAnswersToPassTest;
  }

  public void setAmountOfCorrectAnswersToPassTest(int amountOfCorrectAnswersToPassTest) {
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  public void setLocale(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String toString() {
    return "{"
        + "\"answerTemplate\":\"" + answerTemplate + "\""
        + ", \"questionTemplate\":\"" + questionTemplate + "\""
        + ", \"amountOfCorrectAnswersToPassTest\":\"" + amountOfCorrectAnswersToPassTest + "\""
        + ", \"locale\":" + locale
        + "}";
  }
}
