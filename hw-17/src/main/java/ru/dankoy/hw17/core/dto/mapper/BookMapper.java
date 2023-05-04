package ru.dankoy.hw17.core.dto.mapper;

import ru.dankoy.hw17.core.domain.Book;
import ru.dankoy.hw17.core.dto.BookDTO;

public interface BookMapper {

  BookDTO toDTOWithoutCommentaries(Book book);

  BookDTO toSimpleDTO(Book book);

  BookDTO toDTOWithCommentaries(Book book);

  Book toBook(BookDTO dto);
}
