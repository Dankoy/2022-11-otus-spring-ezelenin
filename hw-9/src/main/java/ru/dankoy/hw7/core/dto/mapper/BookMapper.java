package ru.dankoy.hw7.core.dto.mapper;

import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.dto.BookDTO;
import ru.dankoy.hw7.core.dto.BookFormDTO;

public interface BookMapper {

  BookDTO toDTOWithoutCommentaries(Book book);

  BookDTO toDTOWithCommentaries(Book book);

  BookDTO toSimpleDTO(Book book);

  Book toBook(BookDTO dto);

  BookFormDTO bookFormDTO(Book book);

  Book toBook(BookFormDTO bookFormDTO);
}
