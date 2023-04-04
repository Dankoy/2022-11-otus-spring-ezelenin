package ru.dankoy.hw16.core.dto.book.mapper;

import java.util.HashSet;
import org.springframework.stereotype.Component;
import ru.dankoy.hw16.core.domain.Book;
import ru.dankoy.hw16.core.dto.book.BookDTO;


@Component
public class BookMapperImpl implements BookMapper {

  @Override
  public BookDTO toDTOWithoutCommentaries(Book book) {

    return BookDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(book.getGenres())
        .authors(book.getAuthors())
        .commentaries(new HashSet<>())
        .build();

  }

  @Override
  public BookDTO toDTOWithCommentaries(Book book) {

    return BookDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(book.getGenres())
        .authors(book.getAuthors())
        .commentaries(book.getCommentaries())
        .build();

  }

  @Override
  public BookDTO toSimpleDTO(Book book) {

    return BookDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(new HashSet<>())
        .authors(new HashSet<>())
        .commentaries(new HashSet<>())
        .build();

  }


  @Override
  public Book toBook(BookDTO dto) {

    return new Book(
        dto.getId(),
        dto.getName(),
        dto.getAuthors(),
        dto.getGenres(),
        dto.getCommentaries()
    );

  }

  @Override
  public Book toBookWithoutCommentaries(BookDTO dto) {

    return new Book(
        dto.getId(),
        dto.getName(),
        dto.getAuthors(),
        dto.getGenres(),
        new HashSet<>()
    );

  }

}
