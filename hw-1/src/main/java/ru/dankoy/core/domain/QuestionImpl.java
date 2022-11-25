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
  public String toString() {
    return "{\"QuestionImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"question\":\"" + question + "\""
        + ", \"answer\":" + answers
        + ", \"correctAnswerId\":\"" + correctAnswerId + "\""
        + "}}";
  }
}
