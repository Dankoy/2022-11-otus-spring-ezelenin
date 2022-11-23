package ru.dankoy.otus.core.utils;

import java.util.List;
import ru.dankoy.otus.core.domain.Answer;
import ru.dankoy.otus.core.domain.Question;

public class Printer {

  private final String questionTemplate;
  private final String answerTemplate;

  public Printer(String questionTemplate, String answerTemplate) {
    this.answerTemplate = answerTemplate;
    this.questionTemplate = questionTemplate;
  }


  public void printQuestions(List<Question> questionList) {

    StringBuilder stringBuilder = new StringBuilder();

    for (Question question : questionList) {

      stringBuilder.append(
          String.format(questionTemplate, question.getId(), question.getQuestion()));
      stringBuilder.append(System.getProperty("line.separator"));

      for (Answer answer : question.getAnswers()) {
        stringBuilder.append(String.format(answerTemplate, answer.getId(), answer.getAnswer()));
        stringBuilder.append(System.getProperty("line.separator"));
      }

    }

    String res = stringBuilder.toString();

    System.out.println(res);

  }

}
