package ru.dankoy.hw10;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw10.core.commands.AuthorCommand;
import ru.dankoy.hw10.core.commands.BookCommand;
import ru.dankoy.hw10.core.commands.GenreCommand;
import ru.dankoy.hw10.core.dto.mapper.BookMapper;
import ru.dankoy.hw10.core.repository.author.AuthorRepository;
import ru.dankoy.hw10.core.repository.book.BookRepository;
import ru.dankoy.hw10.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw10.core.service.book.BookServiceMongo;
import ru.dankoy.hw10.core.service.genre.GenreServiceMongo;
import ru.dankoy.hw10.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw10ApplicationDefaultTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("all default necessary beans should be created")
  @Test
  void contextLoads() {

    var bookRepository = context.getBean(BookRepository.class);
    var authorRepository = context.getBean(AuthorRepository.class);
    var bookServiceJpa = context.getBean(BookServiceMongo.class);
    var authorServiceJpa = context.getBean(AuthorServiceMongo.class);
    var genreServiceJpa = context.getBean(GenreServiceMongo.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var authorCommand = context.getBean(AuthorCommand.class);
    var bookCommand = context.getBean(BookCommand.class);
    var genreCommand = context.getBean(GenreCommand.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);
    var bookMapper = context.getBean(BookMapper.class);

    assertNotNull(bookRepository);
    assertNotNull(authorRepository);
    assertNotNull(bookServiceJpa);
    assertNotNull(authorServiceJpa);
    assertNotNull(genreServiceJpa);
    assertNotNull(objectMapper);
    assertNotNull(authorCommand);
    assertNotNull(bookCommand);
    assertNotNull(genreCommand);
    assertNotNull(objectMapperService);
    assertNotNull(bookMapper);

  }

}
