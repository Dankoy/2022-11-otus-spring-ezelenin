package ru.dankoy.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.dankoy.core.dao.csv.CsvQuestionsDaoImpl;
import ru.dankoy.core.service.QuestionsServiceImpl;
import ru.dankoy.core.utils.PrinterImpl;

@SpringJUnitConfig(locations = "/spring-context.xml")
class ContextTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  @DisplayName("Spring context testing")
  void contextTest() {

    assertNotNull(applicationContext);

    assertNotNull(applicationContext.getBean(PrinterImpl.class));
    assertNotNull(applicationContext.getBean(QuestionsServiceImpl.class));
    assertNotNull(applicationContext.getBean(CsvQuestionsDaoImpl.class));

  }


}
