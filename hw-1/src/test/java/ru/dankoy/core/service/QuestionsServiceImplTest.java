package ru.dankoy.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dankoy.core.dao.CsvQuestionsDaoImpl;
import ru.dankoy.core.dao.QuestionsDao;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.AnswerImpl;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.QuestionImpl;

public class QuestionsServiceImplTest {


  private final QuestionsDao questionsDao = Mockito.mock(CsvQuestionsDaoImpl.class);

  @Test
  @DisplayName("Test get questions from dao")
  public void getQuestionsTest() {

    Mockito.when(questionsDao.getQuestions()).thenReturn(correctList());

    var service = new QuestionsServiceImpl(questionsDao);

    assertEquals(service.getQuestions(), correctList());

    Mockito.verify(questionsDao, Mockito.times(1)).getQuestions();

  }

  private List<Question> correctList() {

    List<Answer> answers = List.of(
        new AnswerImpl(1, "добавляя воду"),
        new AnswerImpl(2, "увеличивая концентрацию растворенного вещества"),
        new AnswerImpl(3, "охлаждая раствор"),
        new AnswerImpl(4, "вводя в раствор один из продуктов гидролиза")
    );

    var question = new QuestionImpl(2, "Гидролиз соли в водном растворе возможно усилить …",
        answers, 1);

    return List.of(question);

  }

}
