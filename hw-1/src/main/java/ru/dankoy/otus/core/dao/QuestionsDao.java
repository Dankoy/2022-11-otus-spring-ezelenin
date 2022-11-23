package ru.dankoy.otus.core.dao;

import java.util.List;
import ru.dankoy.otus.core.domain.Question;

/**
 * @author turtality 2022-11-23
 * <p>
 * Common DAO interface for questions
 */
public interface QuestionsDao {

  List<Question> getQuestions();

}
