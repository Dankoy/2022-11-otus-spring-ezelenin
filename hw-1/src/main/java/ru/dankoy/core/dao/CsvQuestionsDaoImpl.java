package ru.dankoy.core.dao;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ru.dankoy.core.domain.Answer;
import ru.dankoy.core.domain.AnswerImpl;
import ru.dankoy.core.domain.Question;
import ru.dankoy.core.domain.QuestionImpl;

/**
 * @author turtality
 * <p>
 * Implementation of {@link QuestionsDao} interface where resource for questions is csv file.
 */
public class CsvQuestionsDaoImpl implements QuestionsDao {

  private final String resource; // строка с указанием на ресурс

  public CsvQuestionsDaoImpl(String resource) {
    this.resource = resource;
  }

  @Override
  public List<Question> getQuestions() {

    try (var reader = new BufferedReader(
        new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(resource))));
        var csvReader = new CSVReaderBuilder(reader)
            .withSkipLines(1)
            .withCSVParser(new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build())
            .build()) {

      List<String[]> rows = csvReader.readAll();

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
            answers.add(new AnswerImpl(answerId, row[i]));

            var isCorrect = Boolean.parseBoolean(row[i + 1]);

            if (isCorrect) {
              correctAnswerId = answerId;
            }
          }
          answerId++;

        }

        var question = new QuestionImpl(questionId, questionText, answers, correctAnswerId);
        questions.add(question);

      }

      return questions;

    } catch (Exception e) {
      throw new QuestionsDaoException(e);
    }
  }
}
