package ru.dankoy.core.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dankoy.core.dao.csv.CsvQuestionsDaoImpl;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.AnswerImpl;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.QuestionImpl;

@DisplayName("Тесты CsvQuestionDaoImpl класса")
class CsvQuestionsDaoImplTest {


  @Test
  @DisplayName("Тестирование парсера csv")
  void parseCsvTest() {

    var questionDao = new CsvQuestionsDaoImpl("/questions-eng.csv");

    List<Question> questionList = questionDao.getQuestions();

    assertEquals(questionList, correctList());

  }

  private List<Question> correctList() {

    List<Answer> answers = List.of(
        new AnswerImpl(1, "Gas only"),
        new AnswerImpl(2, "Both Solid and Liquid"),
        new AnswerImpl(3, "Liquid only"),
        new AnswerImpl(4, "Solid only")
    );

    var question = new QuestionImpl(1, "Young's modulus is the property of",
        answers, 4);

    return List.of(question);

  }


}
