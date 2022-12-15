package ru.dankoy.core.csvreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.dankoy.config.LocaleProvider;
import ru.dankoy.core.exceptions.CsvReaderException;

@DisplayName("Тестирование ридера из файла csv")
@ExtendWith(MockitoExtension.class)
class CsvReaderImplTest {

  private final Locale enLocale = new Locale("en_US");
  @Mock
  private MessageSource messageSource;
  @Mock
  private LocaleProvider localeProvider;
  @InjectMocks
  private CsvReaderImpl csvReader;

  @DisplayName("Test correct read from csv file")
  @ParameterizedTest(name = "locale: {0}, file_name: {1}")
  @CsvSource({"en_US, /questions-eng.csv", "ru_RU, /questions-ru.csv"})
  void readTest(Locale locale, String fileName) {

    given(localeProvider.getLocale()).willReturn(locale);
    given(messageSource.getMessage(anyString(), any(), eq(locale))).willReturn(fileName);

    List<String[]> expected = csvReader.read();

    if (locale.equals(new Locale("en_US"))) {
      assertThat(expected).hasSameElementsAs(correctEngCsvRead());
    } else if (locale.equals(new Locale("ru_RU"))) {
      assertThat(expected).hasSameElementsAs(correctRusCsvRead());
    }


  }

  @DisplayName("Test read from non existing file. Expecting CsvReaderException")
  @Test
  void read_expect_npe_error() {

    given(localeProvider.getLocale()).willReturn(enLocale);
    given(messageSource.getMessage(anyString(), any(), eq(enLocale))).willReturn(
        "/questions-none.csv");

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

}
