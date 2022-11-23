package ru.dankoy.otus.core.domain;


public class AnswerImpl implements Answer {

  private final long id; // id ответа
  private final String answer; // текст ответа

  public AnswerImpl(long id, String answer) {
    this.id = id;
    this.answer = answer;
  }

  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "{\"AnswerImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"answer\":\"" + answer + "\""
        + "}}";
  }
}
