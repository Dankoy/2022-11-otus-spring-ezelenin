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
import ru.dankoy.hw5.core.dao.book.BookDaoHibernate;
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

    var bookDaoHibernate = context.getBean(BookDaoHibernate.class);
    var authorDaoHibernate = context.getBean(AuthorDaoHibernate.class);
    var genreDaoHibernate = context.getBean(GenreDaoHibernate.class);
    var bookServiceHibernate = context.getBean(BookServiceHibernate.class);
    var authorServiceHibernate = context.getBean(AuthorServiceHibernate.class);
    var genreServiceHibernate = context.getBean(GenreServiceHibernate.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var authorCommand = context.getBean(AuthorCommand.class);
    var bookCommand = context.getBean(BookCommand.class);
    var genreCommand = context.getBean(GenreCommand.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);

    assertNotNull(bookDaoHibernate);
    assertNotNull(authorDaoHibernate);
    assertNotNull(genreDaoHibernate);
    assertNotNull(bookServiceHibernate);
    assertNotNull(authorServiceHibernate);
    assertNotNull(genreServiceHibernate);
    assertNotNull(objectMapper);
    assertNotNull(authorCommand);
    assertNotNull(bookCommand);
    assertNotNull(genreCommand);
    assertNotNull(objectMapperService);

  }

}
