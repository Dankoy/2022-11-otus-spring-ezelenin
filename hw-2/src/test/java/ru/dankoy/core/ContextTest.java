package ru.dankoy.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dankoy.Main;
import ru.dankoy.core.csvreader.CsvReader;
import ru.dankoy.core.dao.QuestionsDao;
import ru.dankoy.core.service.io.IOService;
import ru.dankoy.core.service.printer.Printer;
import ru.dankoy.core.service.questions.QuestionsService;
import ru.dankoy.core.service.test.TestingPerformer;
import ru.dankoy.core.service.testresultinterpreter.TestResultInterpreterService;

@SpringJUnitConfig(value = Main.class)
@ActiveProfiles({"default"})
class ContextTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  @DisplayName("Spring context testing")
  void contextTest() {

    assertNotNull(applicationContext);

    assertNotNull(applicationContext.getBean(Printer.class));
    assertNotNull(applicationContext.getBean(QuestionsService.class));
    assertNotNull(applicationContext.getBean(QuestionsDao.class));
    assertNotNull(applicationContext.getBean(CsvReader.class));
    assertNotNull(applicationContext.getBean(IOService.class));
    assertNotNull(applicationContext.getBean(TestingPerformer.class));
    assertNotNull(applicationContext.getBean(TestResultInterpreterService.class));

  }


}
