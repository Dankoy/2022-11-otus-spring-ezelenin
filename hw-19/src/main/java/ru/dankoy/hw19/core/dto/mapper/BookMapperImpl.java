package ru.dankoy.hw19.core.dto.mapper;

import java.util.HashSet;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Book;
import ru.dankoy.hw19.core.dto.BookDTO;


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
  public BookDTO toDTOWithCommentaries(Book book) {

    return BookDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(book.getGenres())
        .authors(book.getAuthors())
        .build();

  }


  @Override
  public Book toBook(BookDTO dto) {

    return new Book(
        dto.getId(),
        dto.getName(),
        dto.getAuthors(),
        dto.getGenres()
    );

  }

}
