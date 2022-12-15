package ru.dankoy.core.domain;


public class Answer {

  private final long id; // id ответа
  private final String answerText; // текст ответа

  public Answer(long id, String answerText) {
    this.id = id;
    this.answerText = answerText;
  }

  public String getAnswerText() {
    return answerText;
  }

  public long getId() {
    return id;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Answer answer1 = (Answer) o;

    if (id != answer1.id) {
      return false;
    }
    return answerText.equals(answer1.answerText);
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + answerText.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "{\"AnswerImpl\":{"
        + "\"id\":\"" + id + "\""
        + ", \"answerText\":\"" + answerText + "\""
        + "}}";
  }
}
