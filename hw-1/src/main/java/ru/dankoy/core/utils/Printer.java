package ru.dankoy.core.utils;

import java.util.List;
import ru.dankoy.core.domain.Question;

public interface Printer {

  void printQuestions(List<Question> questionList);
}
