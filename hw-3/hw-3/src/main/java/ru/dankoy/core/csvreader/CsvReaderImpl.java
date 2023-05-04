package ru.dankoy.core.csvreader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;
import ru.dankoy.config.QuestionsFileNameProvider;
import ru.dankoy.core.aspects.Log;
import ru.dankoy.core.exceptions.CsvReaderException;

@Component
public class CsvReaderImpl implements CsvReader {

  private final QuestionsFileNameProvider appProperties;

  public CsvReaderImpl(QuestionsFileNameProvider appProperties) {
    this.appProperties = appProperties;
  }

  @Log
  @Override
  public List<String[]> read() {
    try (var reader = new BufferedReader(
        new InputStreamReader(
            Objects.requireNonNull(getClass().getResourceAsStream(appProperties.getQuestionsCsv()))));
        var csvReader = new CSVReaderBuilder(reader)
            .withSkipLines(1)
            .withCSVParser(new CSVParserBuilder()
                .withSeparator(';')
                .withIgnoreQuotations(true)
                .build())
            .build()) {

      return csvReader.readAll();

    } catch (Exception e) {
      throw new CsvReaderException(e);
    }
  }
}
