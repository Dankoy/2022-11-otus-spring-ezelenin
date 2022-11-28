package ru.dankoy.core.csvreader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тестирование ридера из файла csv")
class CsvReaderImplTest {

  @DisplayName("Test correct read from csv file")
  @Test
  void readTest() {

    CsvReader csvReader = new CsvReaderImpl("/questions-eng.csv");

    List<String[]> expected = csvReader.read();

    assertThat(expected).hasSameElementsAs(correctCsvRead());

  }

  @DisplayName("Test read from non existing file. Expecting CsvReaderException")
  @Test
  void read_expect_npe_error() {

    CsvReader csvReader = new CsvReaderImpl("/none.csv");

    assertThatThrownBy(csvReader::read).isInstanceOf(CsvReaderException.class);

  }

  private List<String[]> correctCsvRead() {

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

}
