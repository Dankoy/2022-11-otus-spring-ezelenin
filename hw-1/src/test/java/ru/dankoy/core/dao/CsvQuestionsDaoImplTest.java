package ru.dankoy.core.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.AnswerImpl;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.QuestionImpl;

@DisplayName("Тесты CsvQuestionDaoImpl класса")
class CsvQuestionsDaoImplTest {


  @Test
  @DisplayName("Тестирование парсера csv")
  void parseCsvTest() {

    var questionDao = new CsvQuestionsDaoImpl("/questions.csv");

    List<Question> questionList = questionDao.getQuestions();

    assertEquals(questionList, correctList());

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
