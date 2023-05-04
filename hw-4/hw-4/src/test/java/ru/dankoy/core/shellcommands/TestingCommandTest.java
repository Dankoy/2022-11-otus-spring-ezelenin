package ru.dankoy.core.shellcommands;


import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.dankoy.core.domain.Student;
import ru.dankoy.core.domain.TestResult;
import ru.dankoy.core.service.test.TestingPerformer;
import ru.dankoy.core.service.testresultinterpreter.TestResultInterpreterService;

@SpringBootTest
class TestingCommandTest {


  private static final String RUN_TESTS_COMMAND = "test";
  private static final String SHORT_TESTS_COMMAND = "t";
  @MockBean
  private TestingPerformer testingPerformer;
  @MockBean
  private TestResultInterpreterService testResultInterpreterService;
  @Autowired
  private Shell shell;
  private TestResult testResult;


  @BeforeEach
  void setUp() {

    var student = new Student("fn", "ln");
    testResult = new TestResult(3, 5, student);

  }

  @DisplayName(" should correctly run methods of testing services")
  @ParameterizedTest
  @ValueSource(strings = {RUN_TESTS_COMMAND, SHORT_TESTS_COMMAND})
  void shouldPerformTesting(String command) {

    given(testingPerformer.performTest()).willReturn(testResult);

    shell.evaluate(() -> command);

    Mockito.verify(testingPerformer, Mockito.times(1)).performTest();
    Mockito.verify(testResultInterpreterService, Mockito.times(1)).interpretTestResult(testResult);

  }

  @DisplayName(" should not run methods of testing services")
  @ParameterizedTest
  @EmptySource
  void shouldNotPerformTesting(String command) {

    given(testingPerformer.performTest()).willReturn(testResult);

    shell.evaluate(() -> command);

    Mockito.verify(testingPerformer, Mockito.times(0)).performTest();
    Mockito.verify(testResultInterpreterService, Mockito.times(0)).interpretTestResult(testResult);

  }

}
