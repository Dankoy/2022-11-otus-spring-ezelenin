package ru.dankoy.core.service;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.dankoy.core.dao.QuestionsDao;
import ru.dankoy.core.domain.Question;

/**
 * @author Dankoy
 * <p>
 * Implementation of interface {@link QuestionsService} for lower DAO layer {@link QuestionsDao}
 */
@Service
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
