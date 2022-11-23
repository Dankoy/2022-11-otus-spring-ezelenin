package ru.dankoy.otus.core.service;

import java.util.List;
import ru.dankoy.otus.core.domain.Question;

/**
 * @author turtality
 *
 * Service layer for questions
 */
public interface QuestionsService {

  List<Question> getQuestions();
}
