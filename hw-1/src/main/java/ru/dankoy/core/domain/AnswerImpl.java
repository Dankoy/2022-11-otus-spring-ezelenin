package ru.dankoy.core.domain;


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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AnswerImpl answer1 = (AnswerImpl) o;

    if (id != answer1.id) {
      return false;
    }
    return answer.equals(answer1.answer);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + answer.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "{\"AnswerImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"answer\":\"" + answer + "\""
        + "}}";
  }
}
