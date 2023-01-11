package ru.dankoy.hw5;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.BookDaoJdbcMerge;

@DisplayName("Test context conditional on property book.dao.join=false ")
@SpringBootTest
@Import(BookDaoJdbcMerge.class)
@TestPropertySource(properties = "book.dao.join=false")
class Hw5ApplicationContextBeanDaoMergeTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("should create BookDaoJdbcMerge depending on property")
  @Test
  void contextLoads() {

    var bookDaoJdbc = context.getBean(BookDaoJdbcMerge.class);

    assertNotNull(bookDaoJdbc);

  }

}
