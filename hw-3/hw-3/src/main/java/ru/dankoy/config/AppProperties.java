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
    TestEvaluationProvider {

  private final String answerTemplate;
  private final String questionTemplate;
  private final int amountOfCorrectAnswersToPassTest;
  private final Locale locale;

  public AppProperties(String answerTemplate, String questionTemplate,
      int amountOfCorrectAnswersToPassTest, Locale locale) {
    this.answerTemplate = answerTemplate;
    this.questionTemplate = questionTemplate;
    this.amountOfCorrectAnswersToPassTest = amountOfCorrectAnswersToPassTest;
    this.locale = locale;
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
  public String toString() {
    return "{"
        + "\"answerTemplate\":\"" + answerTemplate + "\""
        + ", \"questionTemplate\":\"" + questionTemplate + "\""
        + ", \"amountOfCorrectAnswersToPassTest\":\"" + amountOfCorrectAnswersToPassTest + "\""
        + ", \"locale\":" + locale
        + "}";
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
    return locale.equals(that.locale);
  }

  @Override
  public int hashCode() {
    int result = answerTemplate.hashCode();
    result = 31 * result + questionTemplate.hashCode();
    result = 31 * result + amountOfCorrectAnswersToPassTest;
    result = 31 * result + locale.hashCode();
    return result;
  }
}
