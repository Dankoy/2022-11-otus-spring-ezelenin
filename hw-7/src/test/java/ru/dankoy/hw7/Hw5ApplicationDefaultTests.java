package ru.dankoy.hw7;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw7.core.commands.AuthorCommand;
import ru.dankoy.hw7.core.commands.BookCommand;
import ru.dankoy.hw7.core.commands.GenreCommand;
import ru.dankoy.hw7.core.service.author.AuthorServiceJpa;
import ru.dankoy.hw7.core.service.book.BookServiceJpa;
import ru.dankoy.hw7.core.service.genre.GenreServiceJpa;
import ru.dankoy.hw7.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw5ApplicationDefaultTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("all default necessary beans should be created")
  @Test
  void contextLoads() {

    var bookDaoHibernate = context.getBean(BookRepositoryHibernate.class);
    var genreDaoHibernate = context.getBean(GenreDaoHibernate.class);
    var bookServiceHibernate = context.getBean(BookServiceJpa.class);
    var authorServiceHibernate = context.getBean(AuthorServiceJpa.class);
    var genreServiceHibernate = context.getBean(GenreServiceJpa.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var authorCommand = context.getBean(AuthorCommand.class);
    var bookCommand = context.getBean(BookCommand.class);
    var genreCommand = context.getBean(GenreCommand.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);

    assertNotNull(bookDaoHibernate);
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
