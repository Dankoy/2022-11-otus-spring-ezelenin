package ru.dankoy.hw19.core.service.author;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.dankoy.hw19.core.domain.Author;
import ru.dankoy.hw19.core.domain.Genre;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw19.core.repository.author.AuthorRepository;
import ru.dankoy.hw19.core.service.work.WorkService;
import ru.dankoy.hw19.core.service.work.WorkServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test AuthorServiceMongo ")
@DataMongoTest
@Import({AuthorServiceMongo.class, WorkServiceMongo.class})
class AuthorServiceMongoTest {

//  @MockBean
//  private AuthorRepository authorRepository;
//
//  @MockBean
//  private WorkService workService;
//
//  @Autowired
//  private AuthorServiceMongo authorServiceMongo;
//
//  @Autowired
//  private MongoTemplate mongoTemplate;
//
//
//  @DisplayName("should return all authors")
//  @Test
//  void shouldGetAllAuthorsTest() {
//
//    var correctAuthorList = makeCorrectAllAuthorsList();
//
//    given(authorRepository.findAll()).willReturn(correctAuthorList);
//
//    var authors = authorServiceMongo.getAll();
//
//    assertThat(authors).isEqualTo(correctAuthorList);
//
//    Mockito.verify(authorRepository, times(1)).findAll();
//  }
//
//
//  @DisplayName("should return correct count")
//  @Test
//  void shouldReturnCorrectCountTest() {
//
//    given(authorRepository.count()).willReturn(4L);
//
//    var count = authorServiceMongo.count();
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
//    var expectedAuthor = mongoTemplate.find(new Query()
//            .addCriteria(Criteria.where("name").is("author1")), Author.class)
//        .get(0);
//
//    given(authorRepository.findById(expectedAuthor.getId()))
//        .willReturn(Optional.of(expectedAuthor));
//
//    var actualAuthor = authorServiceMongo.getById(expectedAuthor.getId());
//
//    assertThat(actualAuthor).isPresent().get().isEqualTo(expectedAuthor);
//    Mockito.verify(authorRepository, times(1)).findById(expectedAuthor.getId());
//
//  }

//  @DisplayName("should correctly insert author in db")
//  @Test
//  void shouldCorrectlyInsertAuthor() {
//
//    var authorToInsert = new Author(null, "new_author", null, null, null, null);
//    var insertedAuthor = new Author("whatever", "new_author", null, null, null, null);
//
//    given(authorRepository.save(authorToInsert)).willReturn(insertedAuthor);
//
//    var actual = authorServiceMongo.insertOrUpdate(authorToInsert);
//
//    assertThat(actual).isEqualTo(insertedAuthor);
//    Mockito.verify(authorRepository, times(1)).save(authorToInsert);
//
//  }
//
//  @DisplayName("should correctly deleteByBookId author by id")
//  @Test
//  void shouldCorrectlyDeleteAuthorById() {
//
//    var id = "1L";
//
//    var toDelete = new Author(id, "name", null, null, null, null);
//    List<Work> booksBeforeRemove = makeCorrectAllBooksList();
//    List<Work> booksToDeleteAuthor = makeCorrectAllBooksList();
//
//    deleteAuthorFromBooks(booksToDeleteAuthor, toDelete);
//
//    given(authorRepository.findById(id)).willReturn(Optional.of(toDelete));
//    given(workService.findAllByAuthorId(toDelete)).willReturn(booksBeforeRemove);
//
//    authorServiceMongo.deleteById(id);
//
//    Mockito.verify(authorRepository, times(1)).delete(toDelete);
//    Mockito.verify(workService, times(1)).findAllByAuthorId(toDelete);
//    Mockito.verify(workService, times(1)).updateMultiple(booksToDeleteAuthor);
//
//  }
//
//  @DisplayName("should throw exception when deleting author by id")
//  @Test
//  void shouldThrowExceptionWhenDeleteNonExistingAuthorById() {
//
//    var id = "999";
//
//    given(authorRepository.findById(id)).willReturn(Optional.empty());
//
//    assertThatThrownBy(() -> authorServiceMongo.deleteById(id))
//        .isInstanceOf(EntityNotFoundException.class);
//
//    Mockito.verify(authorRepository, times(0)).delete(any());
//
//  }
//
//  private List<Author> makeCorrectAllAuthorsList() {
//    return mongoTemplate.findAll(Author.class);
//  }
//
//  private List<Work> makeCorrectAllBooksList() {
//
//    Set<Author> authorBook1 = new HashSet<>();
//    authorBook1.add(new Author("1L", "author1", null, null, null, null));
//    authorBook1.add(new Author("2L", "author2", null, null, null, null));
//
//    Set<Author> authorBook2 = new HashSet<>();
//    authorBook2.add(new Author("2L", "author2", null, null, null, null));
//    authorBook2.add(new Author("3L", "author3", null, null, null, null));
//
//    Set<Author> authorBook3 = new HashSet<>();
//    authorBook3.add(new Author("1L", "author1", null, null, null, null));
//    authorBook3.add(new Author("3L", "author3", null, null, null, null));
//
//    var book1 = new Work("1L", "book1", "descr",
//        authorBook1,
//        Set.of(new Genre("genre1"), new Genre("genre2")),
//        null,
//        null,
//        null);
//
//    var book2 = new Work("2L", "book2", "descr",
//        authorBook2,
//        Set.of(new Genre("genre2"), new Genre("genre3")),
//        null,
//        null,
//        null);
//
//    var book3 = new Work("3L", "book3", "descr",
//        authorBook3,
//        Set.of(new Genre("genre1"), new Genre("genre3")),
//        null,
//        null,
//        null);
//
//    return List.of(
//        book1,
//        book2,
//        book3
//    );
//  }
//
//  private void deleteAuthorFromBooks(List<Work> works, Author toDelete) {
//
//    works.forEach(b -> b.getAuthors().remove(toDelete));
//
//  }


}
