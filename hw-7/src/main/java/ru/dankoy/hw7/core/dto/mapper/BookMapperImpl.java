package ru.dankoy.hw7.core.dto.mapper;

import java.util.HashSet;
import org.springframework.stereotype.Component;
import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.dto.BookDTO;


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
  public Book toBook(BookDTO dto) {

    return new Book(
        dto.getId(),
        dto.getName(),
        dto.getAuthors(),
        dto.getGenres(),
        dto.getCommentaries()
    );

  }

}
