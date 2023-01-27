package ru.dankoy.hw8.core.service.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw8.core.domain.Author;
import ru.dankoy.hw8.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw8.core.repository.author.AuthorRepository;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test AuthorServiceJpa ")
//@DataJpaTest
@Import({AuthorServiceMongo.class})
class AuthorServiceMongoTest {


//  @MockBean
//  private AuthorRepository authorRepository;
//
//  @Autowired
//  private AuthorServiceMongo authorServiceJdbc;
//
//
//  @DisplayName("should return all authors")
//  @Test
//  void shouldGetAllAuthorsTest() {
//
//    given(authorRepository.findAll()).willReturn(makeCorrectAllAuthorsList());
//
//    var authors = authorServiceJdbc.getAll();
//
//    assertThat(authors).isEqualTo(makeCorrectAllAuthorsList());
//
//    Mockito.verify(authorRepository, times(1)).findAll();
//  }
//
//
//  @DisplayName("should return correct count")
//  @Test
//  void shouldReturnCorrectCountTest() {
//
//    given(authorRepository.count()).willReturn(3L);
//
//    var count = authorServiceJdbc.count();
//
//    assertThat(count).isEqualTo(makeCorrectAllAuthorsList().size());
//    Mockito.verify(authorRepository, times(1)).count();
//
//  }
//
//  @DisplayName("should return correct author by id")
//  @Test
//  void shouldReturnCorrectAuthorById() {
//
//    var id = 1;
//
//    var authors = makeCorrectAllAuthorsList();
//    var correctAuthor = getAuthorByIdFromList(authors, id);
//
//    given(authorRepository.getById(id)).willReturn(Optional.ofNullable(correctAuthor));
//
//    var author = authorServiceJdbc.getById(id);
//
//    assertThat(author).isPresent().get().isEqualTo(correctAuthor);
//    Mockito.verify(authorRepository, times(1)).getById(id);
//
//  }
//
//  @DisplayName("should correctly insert author in db")
//  @Test
//  void shouldCorrectlyInsertAuthor() {
//
//    var authorName = "author4";
//    var insertedId = 4L;
//
//    var authorToInsert = new Author(0L, authorName);
//    var insertedAuthor = new Author(insertedId, authorName);
//
//    given(authorRepository.save(authorToInsert)).willReturn(insertedAuthor);
//
//    var actual = authorServiceJdbc.insertOrUpdate(authorToInsert);
//
//    assertThat(actual).isEqualTo(insertedAuthor);
//    Mockito.verify(authorRepository, times(1)).save(authorToInsert);
//
//  }
//
//  @DisplayName("should correctly delete author by id")
//  @Test
//  void shouldCorrectlyDeleteAuthorById() {
//
//    var id = 1L;
//
//    var toDelete = new Author(id, "name");
//    given(authorRepository.getById(id)).willReturn(Optional.of(toDelete));
//
//    authorServiceJdbc.deleteById(id);
//
//    Mockito.verify(authorRepository, times(1)).delete(toDelete);
//
//  }
//
//  @DisplayName("should correctly delete author by id")
//  @Test
//  void shouldThrowExceptionWhenDeleteNonExistingAuthorById() {
//
//    var id = 999L;
//
//    given(authorRepository.getById(id)).willReturn(Optional.empty());
//
//    assertThatThrownBy(() -> authorServiceJdbc.deleteById(id))
//        .isInstanceOf(EntityNotFoundException.class);
//
//    Mockito.verify(authorRepository, times(0)).delete(any());
//
//  }
//
//
//  private Author getAuthorByIdFromList(List<Author> authors, long id) {
//
//    var authorOptional = authors.stream().filter(author -> author.getId() == id)
//        .findFirst();
//
//    return authorOptional.orElse(new Author(999999L, "nonexisting"));
//
//  }
//
//  private List<Author> makeCorrectAllAuthorsList() {
//    return List.of(
//        new Author(1L, "author1"),
//        new Author(2L, "author2"),
//        new Author(3L, "author3")
//    );
//  }


}
