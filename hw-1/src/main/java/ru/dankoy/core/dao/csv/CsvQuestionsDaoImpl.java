package ru.dankoy.core.dao.csv;

import java.util.ArrayList;
import java.util.List;
import ru.dankoy.core.csvreader.CsvReader;
import ru.dankoy.core.dao.QuestionsDao;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.Question;

/**
 * @author Dankoy
 * <p>
 * Implementation of {@link QuestionsDao} interface which uses {@link CsvReader} as raw reading csv
 * class
 */
public class CsvQuestionsDaoImpl implements QuestionsDao {

  private final CsvReader csvReader;

  public CsvQuestionsDaoImpl(CsvReader csvReader) {
    this.csvReader = csvReader;
  }

  @Override
  public List<Question> getQuestions() {

    List<String[]> rows = csvReader.read();

    List<Question> questions = new ArrayList<>();
    // get amount of questions
    for (String[] row : rows) {

      var questionId = Long.parseLong(row[0]);
      var questionText = row[1];

      // parse answers. may have different amount
      var correctAnswerId = -1;
      List<Answer> answers = new ArrayList<>();
      var answerId = 1;
      for (int i = 2; i < row.length; i += 2) {

        if (!row[i].isEmpty() || !row[i].isBlank()) {
          answers.add(new Answer(answerId, row[i]));

          var isCorrect = Boolean.parseBoolean(row[i + 1]);

          if (isCorrect) {
            correctAnswerId = answerId;
          }
        }
        answerId++;

      }

      var question = new Question(questionId, questionText, answers, correctAnswerId);
      questions.add(question);

    }

    return questions;

  }
}
