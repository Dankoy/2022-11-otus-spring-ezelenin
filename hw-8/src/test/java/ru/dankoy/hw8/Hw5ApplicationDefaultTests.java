package ru.dankoy.hw8;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw8.core.commands.AuthorCommand;
import ru.dankoy.hw8.core.commands.BookCommand;
import ru.dankoy.hw8.core.commands.GenreCommand;
import ru.dankoy.hw8.core.dto.mapper.BookMapper;
import ru.dankoy.hw8.core.repository.author.AuthorRepository;
import ru.dankoy.hw8.core.repository.book.BookRepository;
import ru.dankoy.hw8.core.repository.genre.GenreRepository;
import ru.dankoy.hw8.core.service.author.AuthorServiceJpa;
import ru.dankoy.hw8.core.service.book.BookServiceJpa;
import ru.dankoy.hw8.core.service.genre.GenreServiceJpa;
import ru.dankoy.hw8.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw5ApplicationDefaultTests {

  @Autowired
  ApplicationContext context;


  @DisplayName("all default necessary beans should be created")
  @Test
  void contextLoads() {

    var bookRepository = context.getBean(BookRepository.class);
    var authorRepository = context.getBean(AuthorRepository.class);
    var genreRepository = context.getBean(GenreRepository.class);
    var bookServiceJpa = context.getBean(BookServiceJpa.class);
    var authorServiceJpa = context.getBean(AuthorServiceJpa.class);
    var genreServiceJpa = context.getBean(GenreServiceJpa.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var authorCommand = context.getBean(AuthorCommand.class);
    var bookCommand = context.getBean(BookCommand.class);
    var genreCommand = context.getBean(GenreCommand.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);
    var bookMapper = context.getBean(BookMapper.class);

    assertNotNull(bookRepository);
    assertNotNull(genreRepository);
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
