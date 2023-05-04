package ru.dankoy.hw13.core.dto.book.mapper;

import ru.dankoy.hw13.core.domain.Book;
import ru.dankoy.hw13.core.dto.book.BookDTO;

public interface BookMapper {

  BookDTO toDTOWithoutCommentaries(Book book);

  BookDTO toDTOWithCommentaries(Book book);

  BookDTO toSimpleDTO(Book book);

  Book toBook(BookDTO dto);

  Book toBookWithoutCommentaries(BookDTO dto);

}
