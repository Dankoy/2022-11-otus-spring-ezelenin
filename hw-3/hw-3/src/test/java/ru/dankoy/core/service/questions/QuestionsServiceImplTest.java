package ru.dankoy.core.service.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dankoy.core.dao.csv.QuestionsDaoCsv;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;

@DisplayName("Test Questions Service ")
@ExtendWith(MockitoExtension.class)
class QuestionsServiceImplTest {

  @Mock
  private QuestionsDaoCsv questionsDao;

  @InjectMocks
  private QuestionsServiceImpl service;

  @Test
  @DisplayName("returns correct questions")
  void shouldReturnCorrectQuestionsFromDao() {

    given(questionsDao.getQuestions()).willReturn(correctList());

    assertEquals(service.getQuestions(), correctList());

    Mockito.verify(questionsDao, Mockito.times(1)).getQuestions();

  }

  private List<Question> correctList() {

    List<Answer> answers = List.of(
        new Answer(1, "добавляя воду"),
        new Answer(2, "увеличивая концентрацию растворенного вещества"),
        new Answer(3, "охлаждая раствор"),
        new Answer(4, "вводя в раствор один из продуктов гидролиза")
    );

    var question = new Question(2, "Гидролиз соли в водном растворе возможно усилить …",
        answers, 1);

    return List.of(question);

  }

}
