package ru.dankoy.core.service;

import java.util.List;
import ru.dankoy.core.dao.QuestionsDao;
import ru.dankoy.core.domain.Question;

/**
 * @author turtality
 * <p>
 * Implementation of interface {@link QuestionsService} for lower DAO layer {@link QuestionsDao}
 */
public class QuestionsServiceImpl implements QuestionsService {

  private final QuestionsDao questionsDao;

  public QuestionsServiceImpl(QuestionsDao questionsDao) {
    this.questionsDao = questionsDao;
  }


  @Override
  public List<Question> getQuestions() {
    return this.questionsDao.getQuestions();
  }

}
