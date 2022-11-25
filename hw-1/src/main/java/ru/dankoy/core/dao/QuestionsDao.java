package ru.dankoy.core.dao;

import java.util.List;
import ru.dankoy.core.domain.Question;

/**
 * @author Dankoy 2022-11-23
 * <p>
 * Common DAO interface for questions
 */
public interface QuestionsDao {

  List<Question> getQuestions();

}
