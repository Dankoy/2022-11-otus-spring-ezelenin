package ru.dankoy.core.csvreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Locale.Builder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.dankoy.config.AppConfigProperties;
import ru.dankoy.config.QuestionsFileNameProvider;
import ru.dankoy.core.exceptions.CsvReaderException;

@DisplayName("Csv reader ")
@SpringBootTest
class CsvReaderImplTest {

  @MockBean
  private QuestionsFileNameProvider questionsFileNameProvider;
  @Autowired
  private CsvReaderImpl csvReader;

  @DisplayName("should correctly read from csv file")
  @ParameterizedTest(name = "locale: {0}, file_name: {1}")
  @CsvSource({"en_US, /questions-en_US.csv", "ru_RU, /questions-ru_RU.csv"})
  void shouldCorrectlyReadFiles(Locale locale, String fileName) {

    given(questionsFileNameProvider.getQuestionsCsv()).willReturn(fileName);

    List<String[]> expected = csvReader.read();

    if (locale.equals(new Builder().setLanguage("en").setRegion("US").build())) {
      assertThat(expected).hasSameElementsAs(correctEngCsvRead());
    } else if (locale.equals(new Builder().setLanguage("ru").setRegion("RU").build())) {
      assertThat(expected).hasSameElementsAs(correctRusCsvRead());
    }


  }

  @DisplayName("should throw npe while reading non existing file")
  @Test
  void shouldReturnCsvReaderException() {

    given(questionsFileNameProvider.getQuestionsCsv()).willReturn("fileName");

    assertThatThrownBy(csvReader::read).isInstanceOf(CsvReaderException.class);

  }

  private List<String[]> correctEngCsvRead() {

    String[] keys = new String[]{
        "1", "Young's modulus is the property of",
        "Gas only", "FALSE",
        "Both Solid and Liquid", "FALSE",
        "Liquid only", "FALSE",
        "Solid only", "TRUE"
    };

    List<String[]> result = new ArrayList<>();

    result.add(keys);

    return result;

  }

  private List<String[]> correctRusCsvRead() {

    String[] keys = new String[]{
        "1", "Модуль Юнга является свойством",
        "газа", "FALSE",
        "газа и жидкости", "FALSE",
        "жидкости", "FALSE",
        "твердое тело", "TRUE"
    };

    List<String[]> result = new ArrayList<>();

    result.add(keys);

    return result;

  }

  @Configuration()
  @Import({AppConfigProperties.class, CsvReaderImpl.class})
  static class Config {

  }

}
