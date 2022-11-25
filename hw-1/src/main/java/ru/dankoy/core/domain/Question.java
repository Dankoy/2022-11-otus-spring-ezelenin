package ru.dankoy.core.domain;

import java.util.List;

/**
 * @author turtality
 * <p>
 * Question interface
 */
public interface Question {

  String getQuestion();

  List<Answer> getAnswers();

  long getCorrectAnswerId();

  long getId();
}
