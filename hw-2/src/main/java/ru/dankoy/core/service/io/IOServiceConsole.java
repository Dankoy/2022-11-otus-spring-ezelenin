package ru.dankoy.core.service.io;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import ru.dankoy.core.exceptions.InvalidInputException;

public class IOServiceConsole implements IOService {

  private final PrintStream printStream;
  private final Scanner inputStream;

  public IOServiceConsole(PrintStream printStream, InputStream inputStream) {
    this.printStream = printStream;
    this.inputStream = new Scanner(inputStream);
  }

  @Override
  public void print(String message) {
    printStream.println(message);
  }

  @Override
  public String readLn() {
    return inputStream.next();
  }

  @Override
  public long readLong() {
    try {
      return inputStream.nextLong();
    } catch (InputMismatchException e) {
      throw new InvalidInputException("Expected number input", e);
    }

  }

}
