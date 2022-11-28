package ru.dankoy.core.domain;

import java.util.List;

public class Question {

  private final long id;
  private final String questionText; // вопрос
  private final List<Answer> answers; // возможные ответы
  private final long correctAnswerId; // id корректного ответа

  public Question(long id, String questionText, List<Answer> answers, long correctAnswerId) {
    this.id = id;
    this.questionText = questionText;
    this.answers = answers;
    this.correctAnswerId = correctAnswerId;
  }

  public String getQuestionText() {
    return questionText;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public long getCorrectAnswerId() {
    return correctAnswerId;
  }

  public long getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Question question1 = (Question) o;

    if (id != question1.id) {
      return false;
    }
    if (correctAnswerId != question1.correctAnswerId) {
      return false;
    }
    if (!questionText.equals(question1.questionText)) {
      return false;
    }
    return answers.equals(question1.answers);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + questionText.hashCode();
    result = 31 * result + answers.hashCode();
    result = 31 * result + (int) (correctAnswerId ^ (correctAnswerId >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "{\"QuestionImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"questionText\":\"" + questionText + "\""
        + ", \"answer\":" + answers
        + ", \"correctAnswerId\":\"" + correctAnswerId + "\""
        + "}}";
  }
}
