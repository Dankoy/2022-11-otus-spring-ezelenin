package ru.dankoy.otus.core.service;

import java.util.List;
import ru.dankoy.otus.core.dao.QuestionsDao;
import ru.dankoy.otus.core.domain.Question;

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
