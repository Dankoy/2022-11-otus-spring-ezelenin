package ru.dankoy.core.csvreader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CsvReaderImpl implements CsvReader {

  private final String resource;

  public CsvReaderImpl(@Value("${questionsCsv}") String resource) {
    this.resource = resource;
  }

  @Override
  public List<String[]> read() {
    try (var reader = new BufferedReader(
        new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(resource))));
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
