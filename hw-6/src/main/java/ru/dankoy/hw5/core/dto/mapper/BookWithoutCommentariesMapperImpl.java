package ru.dankoy.hw5.core.dto.mapper;

import java.util.HashSet;
import org.springframework.stereotype.Component;
import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.dto.BookWithoutCommentariesDTO;


@Component
public class BookWithoutCommentariesMapperImpl implements BookMapper {

  @Override
  public BookWithoutCommentariesDTO toDTO(Book book) {

    return BookWithoutCommentariesDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(book.getGenres())
        .authors(book.getAuthors())
        .build();

  }


  @Override
  public Book toBook(BookWithoutCommentariesDTO dto) {

    return new Book(
        dto.getId(),
        dto.getName(),
        dto.getAuthors(),
        dto.getGenres(),
        new HashSet<>()
    );

  }

}
