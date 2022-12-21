package ru.dankoy.core.service.io;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test IOServiceConsole")
class IOServiceConsoleTest {

  private static final String TEXT_TO_PRINT1 = "text1";
  private static final String TEXT_TO_PRINT2 = "text2";

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

}
