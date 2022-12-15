package ru.dankoy.core.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.dankoy.core.csvreader.CsvReader;
import ru.dankoy.core.dao.csv.QuestionsDaoCsv;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;

@DisplayName("Тесты CsvQuestionDaoImpl класса")
@ExtendWith(MockitoExtension.class)
class QuestionsDaoCsvTest {

  @Mock
  private CsvReader csvReader;

  @InjectMocks
  private QuestionsDaoCsv csvQuestionsDao;

  @Test
  @DisplayName("Тестирование парсера csv")
  void parseCsvTest() {

    given(csvReader.read()).willReturn(correctCsvRead());

    List<Question> questionList = csvQuestionsDao.getQuestions();

    assertEquals(questionList, correctList());

  }

  private List<Question> correctList() {

    List<Answer> answers = List.of(
        new Answer(1, "Gas only"),
        new Answer(2, "Both Solid and Liquid"),
        new Answer(3, "Liquid only"),
        new Answer(4, "Solid only")
    );

    var question = new Question(1, "Young's modulus is the property of",
        answers, 4);

    return List.of(question);

  }

  private List<String[]> correctCsvRead() {

    String[] keys = new String[]{
        "1", "Young's modulus is the property of",
        "Gas only", "false",
        "Both Solid and Liquid", "false",
        "Liquid only", "false",
        "Solid only", "true"
    };

    List<String[]> result = new ArrayList<>();

    result.add(keys);

    return result;

  }


}
