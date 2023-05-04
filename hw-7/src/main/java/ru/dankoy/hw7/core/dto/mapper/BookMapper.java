package ru.dankoy.hw7.core.dto.mapper;

import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.dto.BookDTO;

public interface BookMapper {

  BookDTO toDTOWithoutCommentaries(Book book);

  BookDTO toSimpleDTO(Book book);

  Book toBook(BookDTO dto);
}
