package ru.dankoy.config;

import java.util.Locale;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * @author turtality
 * <p>
 * Aggregates certain properties from yml file.
 */
@ConfigurationProperties(prefix = "hw3")
@ConstructorBinding
public class AppProperties implements LocaleProvider, PrinterPropertiesProvider,
    TestEvaluationProvider, QuestionsFileNameProvider {

  private final String answerTemplate;
  private final String questionTemplate;
  private final int amountOfCorrectAnswersToPassTest;
  private final Locale locale;
  private final String questionsCsv;

  public AppProperties(String answerTemplate, String questionTemplate,
      int amountOfCorrectAnswersToPassTest, Locale locale, String questionsCsv) {
    this.answerTemplate = answerTemplate;
    this.questionTemplate = questionTemplate;
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
    this.locale = locale;
    this.questionsCsv = questionsCsv;
  }

  @Override
  public String getAnswerTemplate() {
    return answerTemplate;
  }

  @Override
  public String getQuestionTemplate() {
    return questionTemplate;
  }

  @Override
  public int getAmountOfCorrectAnswersToPassTest() {
    return amountOfCorrectAnswersToPassTest;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public String getQuestionsCsv() {
    return questionsCsv;
  }

  @Override
  public String toString() {
    return "{\"AppProperties\":{"
        + "\"answerTemplate\":\"" + answerTemplate + "\""
        + ", \"questionTemplate\":\"" + questionTemplate + "\""
        + ", \"amountOfCorrectAnswersToPassTest\":\"" + amountOfCorrectAnswersToPassTest + "\""
        + ", \"locale\":" + locale
        + ", \"questionsCsv\":\"" + questionsCsv + "\""
        + "}}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AppProperties)) {
      return false;
    }

    AppProperties that = (AppProperties) o;

    if (amountOfCorrectAnswersToPassTest != that.amountOfCorrectAnswersToPassTest) {
      return false;
    }
    if (!answerTemplate.equals(that.answerTemplate)) {
      return false;
    }
    if (!questionTemplate.equals(that.questionTemplate)) {
      return false;
    }
    if (!locale.equals(that.locale)) {
      return false;
    }
    return questionsCsv.equals(that.questionsCsv);
  }

  @Override
  public int hashCode() {
    int result = answerTemplate.hashCode();
    result = 31 * result + questionTemplate.hashCode();
    result = 31 * result + amountOfCorrectAnswersToPassTest;
    result = 31 * result + locale.hashCode();
    result = 31 * result + questionsCsv.hashCode();
    return result;
  }
}
