package ru.dankoy.hw7.core.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ru.dankoy.hw7.core.domain.Author;
import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.domain.Genre;
import ru.dankoy.hw7.core.dto.BookDTO;
import ru.dankoy.hw7.core.dto.BookFormDTO;


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
  public BookFormDTO bookFormDTO(Book book) {

    return BookFormDTO.builder()
        .id(book.getId())
        .name(book.getName())
        .genres(book.getGenres().stream().map(Genre::getId).collect(Collectors.toSet()))
        .authors(book.getAuthors().stream().map(Author::getId).collect(Collectors.toSet()))
        .build();

  }

  @Override
  public Book toBook(BookFormDTO bookFormDTO) {

    return new Book(
        bookFormDTO.getId(),
        bookFormDTO.getName(),
        bookFormDTO.getAuthors().stream().map(Author::new).collect(Collectors.toSet()),
        bookFormDTO.getGenres().stream().map(Genre::new).collect(Collectors.toSet()),
        new HashSet<>()
    );

  }

}
