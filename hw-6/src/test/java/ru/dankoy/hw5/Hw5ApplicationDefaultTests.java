package ru.dankoy.hw5;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw5.core.commands.AuthorCommand;
import ru.dankoy.hw5.core.commands.BookCommand;
import ru.dankoy.hw5.core.commands.GenreCommand;
import ru.dankoy.hw5.core.dao.author.AuthorDaoHibernate;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybyjoin.BookDaoJdbcJoin;
import ru.dankoy.hw5.core.dao.genre.GenreDaoHibernate;
import ru.dankoy.hw5.core.service.author.AuthorServiceHibernate;
import ru.dankoy.hw5.core.service.book.BookServiceHibernate;
import ru.dankoy.hw5.core.service.genre.GenreServiceHibernate;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw5ApplicationDefaultTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("all default necessary beans should be created")
  @Test
  void contextLoads() {

    var bookDaoJdbc = context.getBean(BookDaoJdbcJoin.class);
    var authorDaoJdbc = context.getBean(AuthorDaoHibernate.class);
    var genreDaoJdbc = context.getBean(GenreDaoHibernate.class);
    var bookServiceJdbc = context.getBean(BookServiceHibernate.class);
    var authorServiceJdbc = context.getBean(AuthorServiceHibernate.class);
    var genreServiceJdbc = context.getBean(GenreServiceHibernate.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var authorCommand = context.getBean(AuthorCommand.class);
    var bookCommand = context.getBean(BookCommand.class);
    var genreCommand = context.getBean(GenreCommand.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);

    assertNotNull(bookDaoJdbc);
    assertNotNull(authorDaoJdbc);
    assertNotNull(genreDaoJdbc);
    assertNotNull(bookServiceJdbc);
    assertNotNull(authorServiceJdbc);
    assertNotNull(genreServiceJdbc);
    assertNotNull(objectMapper);
    assertNotNull(authorCommand);
    assertNotNull(bookCommand);
    assertNotNull(genreCommand);
    assertNotNull(objectMapperService);

  }

}
