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
import ru.dankoy.hw5.core.dao.author.AuthorDaoJdbc;
import ru.dankoy.hw5.core.dao.book.mergemanytomanybycode.BookDaoJdbc;
import ru.dankoy.hw5.core.dao.genre.GenreDaoJdbc;
import ru.dankoy.hw5.core.service.author.AuthorServiceJdbc;
import ru.dankoy.hw5.core.service.book.BookServiceJdbc;
import ru.dankoy.hw5.core.service.genre.GenreServiceJdbc;
import ru.dankoy.hw5.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test context ")
@SpringBootTest
class Hw5ApplicationTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("all necessary beans should be created")
  @Test
  void contextLoads() {

    var bookDaoJdbc = context.getBean(BookDaoJdbc.class);
    var authorDaoJdbc = context.getBean(AuthorDaoJdbc.class);
    var genreDaoJdbc = context.getBean(GenreDaoJdbc.class);
    var bookServiceJdbc = context.getBean(BookServiceJdbc.class);
    var authorServiceJdbc = context.getBean(AuthorServiceJdbc.class);
    var genreServiceJdbc = context.getBean(GenreServiceJdbc.class);
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
