package ru.dankoy.hw16.core.service.author;


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
import ru.dankoy.hw16.core.domain.Author;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.domain.Genre;
import ru.dankoy.hw16.core.exceptions.EntityNotFoundException;
import ru.dankoy.hw16.core.repository.author.AuthorRepository;
import ru.dankoy.hw16.core.service.book.BookService;
import ru.dankoy.hw16.core.service.book.BookServiceMongo;


@Transactional(propagation = Propagation.NEVER)
@DisplayName("Test AuthorServiceMongo ")
@DataMongoTest
@Import({AuthorServiceMongo.class, BookServiceMongo.class})
class AuthorServiceMongoTest {

  @MockBean
  private AuthorRepository authorRepository;

  @MockBean
  private BookService bookService;

  @Autowired
  private AuthorServiceMongo authorServiceMongo;

  @Autowired
  private MongoTemplate mongoTemplate;


  @DisplayName("should return all authors")
  @Test
  void shouldGetAllAuthorsTest() {

    var correctAuthorList = makeCorrectAllAuthorsList();

    given(authorRepository.findAll()).willReturn(correctAuthorList);

    var authors = authorServiceMongo.getAll();

    assertThat(authors).isEqualTo(correctAuthorList);

    Mockito.verify(authorRepository, times(1)).findAll();
  }


  @DisplayName("should return correct count")
  @Test
  void shouldReturnCorrectCountTest() {

    given(authorRepository.count()).willReturn(4L);

    var count = authorServiceMongo.count();

    assertThat(count).isEqualTo(makeCorrectAllAuthorsList().size());
    Mockito.verify(authorRepository, times(1)).count();

  }

  @DisplayName("should return correct author by id")
  @Test
  void shouldReturnCorrectAuthorById() {

    var expectedAuthor = mongoTemplate.find(new Query()
            .addCriteria(Criteria.where("name").is("author1")), Author.class)
        .get(0);

    given(authorRepository.findById(expectedAuthor.getId()))
        .willReturn(Optional.of(expectedAuthor));

    var actualAuthor = authorServiceMongo.getById(expectedAuthor.getId());

    assertThat(actualAuthor).isPresent().get().isEqualTo(expectedAuthor);
    Mockito.verify(authorRepository, times(1)).findById(expectedAuthor.getId());

  }

  @DisplayName("should correctly insert author in db")
  @Test
  void shouldCorrectlyInsertAuthor() {

    var authorToInsert = new Author(null, "new_author");
    var insertedAuthor = new Author("whatever", "new_author");

    given(authorRepository.save(authorToInsert)).willReturn(insertedAuthor);

    var actual = authorServiceMongo.insertOrUpdate(authorToInsert);

    assertThat(actual).isEqualTo(insertedAuthor);
    Mockito.verify(authorRepository, times(1)).save(authorToInsert);

  }

  @DisplayName("should correctly deleteByBookId author by id")
  @Test
  void shouldCorrectlyDeleteAuthorById() {

    var id = "1L";

    var toDelete = new Author(id, "name");
    List<Book> booksBeforeRemove = makeCorrectAllBooksList();
    List<Book> booksToDeleteAuthor = makeCorrectAllBooksList();

    deleteAuthorFromBooks(booksToDeleteAuthor, toDelete);

    given(authorRepository.findById(id)).willReturn(Optional.of(toDelete));
    given(bookService.findAllByAuthorId(toDelete)).willReturn(booksBeforeRemove);

    authorServiceMongo.deleteById(id);

    Mockito.verify(authorRepository, times(1)).delete(toDelete);
    Mockito.verify(bookService, times(1)).findAllByAuthorId(toDelete);
    Mockito.verify(bookService, times(1)).updateMultiple(booksToDeleteAuthor);

  }

  @DisplayName("should throw exception when deleting author by id")
  @Test
  void shouldThrowExceptionWhenDeleteNonExistingAuthorById() {

    var id = "999";

    given(authorRepository.findById(id)).willReturn(Optional.empty());

    assertThatThrownBy(() -> authorServiceMongo.deleteById(id))
        .isInstanceOf(EntityNotFoundException.class);

    Mockito.verify(authorRepository, times(0)).delete(any());

  }

  private List<Author> makeCorrectAllAuthorsList() {
    return mongoTemplate.findAll(Author.class);
  }

  private List<Book> makeCorrectAllBooksList() {

    Set<Author> authorBook1 = new HashSet<>();
    authorBook1.add(new Author("1L", "author1"));
    authorBook1.add(new Author("2L", "author2"));

    Set<Author> authorBook2 = new HashSet<>();
    authorBook2.add(new Author("2L", "author2"));
    authorBook2.add(new Author("3L", "author3"));

    Set<Author> authorBook3 = new HashSet<>();
    authorBook3.add(new Author("1L", "author1"));
    authorBook3.add(new Author("3L", "author3"));

    var book1 = new Book("1L", "book1",
        authorBook1,
        Set.of(new Genre("genre1"), new Genre("genre2")));

    var book2 = new Book("2L", "book2",
        authorBook2,
        Set.of(new Genre("genre2"), new Genre("genre3")));

    var book3 = new Book("3L", "book3",
        authorBook3,
        Set.of(new Genre("genre1"), new Genre("genre3")));

    return List.of(
        book1,
        book2,
        book3
    );
  }

  private void deleteAuthorFromBooks(List<Book> books, Author toDelete) {

    books.forEach(b -> b.getAuthors().remove(toDelete));

  }


}
