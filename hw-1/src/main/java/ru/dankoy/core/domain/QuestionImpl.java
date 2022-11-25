package ru.dankoy.core.domain;

import java.util.List;

public class QuestionImpl implements Question {

  private final long id;
  private final String question; // вопрос
  private final List<Answer> answers; // возможные ответы
  private final long correctAnswerId; // id корректного ответа

  public QuestionImpl(long id, String question, List<Answer> answers, long correctAnswerId) {
    this.id = id;
    this.question = question;
    this.answers = answers;
    this.correctAnswerId = correctAnswerId;
  }

  @Override
  public String getQuestion() {
    return question;
  }

  @Override
  public List<Answer> getAnswers() {
    return answers;
  }

  @Override
  public long getCorrectAnswerId() {
    return correctAnswerId;
  }

  @Override
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

    QuestionImpl question1 = (QuestionImpl) o;

    if (id != question1.id) {
      return false;
    }
    if (correctAnswerId != question1.correctAnswerId) {
      return false;
    }
    if (!question.equals(question1.question)) {
      return false;
    }
    return answers.equals(question1.answers);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + question.hashCode();
    result = 31 * result + answers.hashCode();
    result = 31 * result + (int) (correctAnswerId ^ (correctAnswerId >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return "{\"QuestionImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"question\":\"" + question + "\""
        + ", \"answer\":" + answers
        + ", \"correctAnswerId\":\"" + correctAnswerId + "\""
        + "}}";
  }
}
