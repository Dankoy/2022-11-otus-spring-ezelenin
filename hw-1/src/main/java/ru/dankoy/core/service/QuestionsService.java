package ru.dankoy.core.service;

import java.util.List;
import ru.dankoy.core.domain.Question;

/**
 * @author Dankoy
 * <p>
 * Service layer for questions
 */
public interface QuestionsService {

  List<Question> getQuestions();
}
