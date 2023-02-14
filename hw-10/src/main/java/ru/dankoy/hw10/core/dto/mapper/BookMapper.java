package ru.dankoy.hw10.core.dto.mapper;

import ru.dankoy.hw10.core.domain.Book;
import ru.dankoy.hw10.core.dto.BookDTO;

public interface BookMapper {

  BookDTO toDTOWithoutCommentaries(Book book);

  BookDTO toSimpleDTO(Book book);

  Book toBook(BookDTO dto);
}
