package ru.dankoy.core.csvreader;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.dankoy.config.LocaleProvider;
import ru.dankoy.core.aspects.Log;
import ru.dankoy.core.exceptions.CsvReaderException;

@Component
public class CsvReaderImpl implements CsvReader {

  private final MessageSource messageSource;
  private final LocaleProvider appProperties;

  public CsvReaderImpl(MessageSource messageSource, LocaleProvider appProperties) {
    this.messageSource = messageSource;
    this.appProperties = appProperties;
  }

  @Log
  @Override
  public List<String[]> read() {
    try (var reader = new BufferedReader(
        new InputStreamReader(
            Objects.requireNonNull(getClass().getResourceAsStream(
                messageSource.getMessage("questionsCsv", null, appProperties.getLocale())))));
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
