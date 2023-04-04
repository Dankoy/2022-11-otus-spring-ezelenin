package ru.dankoy.hw16.core.repository.book;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.dankoy.hw16.core.domain.Book;


@DisplayName("Test BookRepositoryJpa ")
@DataJpaTest
class BookRepositoryJpaTest {

  @Autowired
  private BookRepositoryImpl bookRepository;

  @Autowired
  private TestEntityManager testEntityManager;


  @DisplayName("should return correct book by id")
  @Test
  void shouldReturnCorrectBookById() {

    var id = 1L;

    var expected = testEntityManager.find(Book.class, id);

    var book = bookRepository.getById(id);

    assertThat(book).isPresent().get().isEqualTo(expected);

  }


}
