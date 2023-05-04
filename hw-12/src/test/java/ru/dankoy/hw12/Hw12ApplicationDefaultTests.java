package ru.dankoy.hw12;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw12.core.controller.BookController;
import ru.dankoy.hw12.core.controller.CommentaryController;
import ru.dankoy.hw12.core.dto.book.mapper.BookMapper;
import ru.dankoy.hw12.core.dto.commentary.mapper.CommentaryMapper;
import ru.dankoy.hw12.core.repository.author.AuthorRepository;
import ru.dankoy.hw12.core.repository.book.BookRepository;
import ru.dankoy.hw12.core.repository.genre.GenreRepository;
import ru.dankoy.hw12.core.service.author.AuthorServiceJpa;
import ru.dankoy.hw12.core.service.book.BookDtoServiceImpl;
import ru.dankoy.hw12.core.service.book.BookServiceJpa;
import ru.dankoy.hw12.core.service.commentary.CommentaryDtoServiceImpl;
import ru.dankoy.hw12.core.service.genre.GenreServiceJpa;
import ru.dankoy.hw12.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw12ApplicationDefaultTests {

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
    var bookDtoService = context.getBean(BookDtoServiceImpl.class);
    var commentaryDtoService = context.getBean(CommentaryDtoServiceImpl.class);
    var objectMapper = context.getBean(ObjectMapper.class);
    var bookController = context.getBean(BookController.class);
    var commentaryController = context.getBean(CommentaryController.class);
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);
    var bookMapper = context.getBean(BookMapper.class);
    var commentaryMapper = context.getBean(CommentaryMapper.class);

    assertNotNull(bookRepository);
    assertNotNull(genreRepository);
    assertNotNull(authorRepository);
    assertNotNull(bookServiceJpa);
    assertNotNull(authorServiceJpa);
    assertNotNull(genreServiceJpa);
    assertNotNull(bookDtoService);
    assertNotNull(commentaryDtoService);
    assertNotNull(objectMapper);
    assertNotNull(bookController);
    assertNotNull(commentaryController);
    assertNotNull(commentaryMapper);
    assertNotNull(objectMapperService);
    assertNotNull(bookMapper);

  }

}
