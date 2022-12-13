package ru.dankoy.config;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hw3")
public class AppProperties {

  private String answerTemplate;
  private String questionTemplate;
  private String amountOfCorrectAnswersToPassTest;
  private Locale locale;

  public String getAnswerTemplate() {
    return answerTemplate;
  }

  public void setAnswerTemplate(String answerTemplate) {
    this.answerTemplate = answerTemplate;
  }

  public String getQuestionTemplate() {
    return questionTemplate;
  }

  public void setQuestionTemplate(String questionTemplate) {
    this.questionTemplate = questionTemplate;
  }

  public String getAmountOfCorrectAnswersToPassTest() {
    return amountOfCorrectAnswersToPassTest;
  }

  public void setAmountOfCorrectAnswersToPassTest(String amountOfCorrectAnswersToPassTest) {
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
  }

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
