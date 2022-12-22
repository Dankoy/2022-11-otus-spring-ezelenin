package ru.dankoy.core.service.io;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dankoy.core.exceptions.InvalidInputException;

@DisplayName("Test IOServiceConsole")
class IOServiceConsoleTest {

  private static final String TEXT_TO_PRINT1 = "text1";
  private static final String TEXT_TO_PRINT2 = "text2";
  private final InputStream systemIn = System.in;
  private final PrintStream systemOut = System.out;
  private ByteArrayOutputStream byteArrayOutputStream;
  private IOService ioService;

  @BeforeEach
  void setUp() {
    byteArrayOutputStream = new ByteArrayOutputStream();
    ioService = new IOServiceConsole(new PrintStream(byteArrayOutputStream), System.in);
  }

  @DisplayName("should print \"" + TEXT_TO_PRINT1 + "\"")
  @Test
  void shouldPrintOnlyFirstTextLine() {
    ioService.print(TEXT_TO_PRINT1);
    assertThat(byteArrayOutputStream)
        .hasToString(TEXT_TO_PRINT1 + System.lineSeparator());
  }

  @DisplayName("should print \"" + TEXT_TO_PRINT2 + "\"")
  @Test
  void shouldPrintOnlySecondTextLine() {
    ioService.print(TEXT_TO_PRINT2);
    assertThat(byteArrayOutputStream)
        .hasToString(TEXT_TO_PRINT2 + System.lineSeparator());
  }

  @Test
  void shouldReadLongFromConsoleInput_throwsInvalid() {

    byteArrayOutputStream = new ByteArrayOutputStream();
    var byteArrayInputStream = new ByteArrayInputStream("text".getBytes());
    ioService = new IOServiceConsole(new PrintStream(byteArrayOutputStream), byteArrayInputStream);

    assertThatThrownBy(() -> ioService.readLong()).isInstanceOf(InvalidInputException.class);

  }

  @AfterEach
  public void restoreSystemInputOutput() {
    System.setIn(systemIn);
    System.setOut(systemOut);
  }

}
