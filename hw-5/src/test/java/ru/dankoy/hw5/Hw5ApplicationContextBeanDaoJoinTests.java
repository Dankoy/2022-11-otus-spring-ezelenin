package ru.dankoy.hw5;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.BookDaoJdbcJoin;

@DisplayName("Test context conditional on property book.dao.join=true ")
@SpringBootTest
@Import(BookDaoJdbcJoin.class)
@TestPropertySource(properties = "book.dao.join=true")
class Hw5ApplicationContextBeanDaoJoinTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("should create BookDaoJdbcJoin depending on property")
  @Test
  void contextLoads() {

    var bookDaoJdbc = context.getBean(BookDaoJdbcJoin.class);

    assertNotNull(bookDaoJdbc);

  }

}
