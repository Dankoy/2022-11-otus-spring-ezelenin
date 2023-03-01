package ru.dankoy.hw11;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.dankoy.hw11.core.controller.BookRestController;
import ru.dankoy.hw11.core.controller.CommentaryRestController;
import ru.dankoy.hw11.core.dto.mapper.BookMapper;
import ru.dankoy.hw11.core.dto.mapper.CommentaryMapper;
import ru.dankoy.hw11.core.repository.author.AuthorRepository;
import ru.dankoy.hw11.core.repository.book.BookRepository;
import ru.dankoy.hw11.core.service.author.AuthorServiceMongo;
import ru.dankoy.hw11.core.service.book.BookServiceMongo;
import ru.dankoy.hw11.core.service.genre.GenreServiceMongo;
import ru.dankoy.hw11.core.service.objectmapper.ObjectMapperServiceImpl;

@DisplayName("Test default context ")
@SpringBootTest
class Hw11ApplicationDefaultTests {

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
    var objectMapperService = context.getBean(ObjectMapperServiceImpl.class);
    var bookMapper = context.getBean(BookMapper.class);
    var commentaryMapper = context.getBean(CommentaryMapper.class);
    var bookRestController = context.getBean(BookRestController.class);
    var commentaryRestController = context.getBean(CommentaryRestController.class);

    assertNotNull(bookRepository);
    assertNotNull(authorRepository);
    assertNotNull(bookServiceJpa);
    assertNotNull(authorServiceJpa);
    assertNotNull(genreServiceJpa);
    assertNotNull(objectMapper);
    assertNotNull(commentaryMapper);
    assertNotNull(commentaryRestController);
    assertNotNull(bookRestController);
    assertNotNull(objectMapperService);
    assertNotNull(bookMapper);

  }

}
