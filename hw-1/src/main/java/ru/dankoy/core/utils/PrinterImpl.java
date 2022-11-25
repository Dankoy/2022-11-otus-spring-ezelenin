package ru.dankoy.core.utils;

import java.util.List;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;

public class PrinterImpl implements Printer {

  private final String questionTemplate;
  private final String answerTemplate;

  public PrinterImpl(String questionTemplate, String answerTemplate) {
    this.answerTemplate = answerTemplate;
    this.questionTemplate = questionTemplate;
  }


  @Override
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

      stringBuilder.append(System.getProperty("line.separator"));

    }

    String res = stringBuilder.toString();

    System.out.println(res);

  }

}
