package ru.dankoy.otus.core.domain;

import java.util.List;

/**
 * @author turtality
 *
 * Question interface
 */
public interface Question {

  String getQuestion();

  List<Answer> getAnswers();

  long getCorrectAnswerId();

  long getId();
}
