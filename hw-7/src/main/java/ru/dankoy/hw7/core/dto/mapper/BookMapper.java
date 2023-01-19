package ru.dankoy.hw7.core.dto.mapper;

import ru.dankoy.hw7.core.domain.Book;
import ru.dankoy.hw7.core.dto.BookWithoutCommentariesDTO;

public interface BookMapper {

  BookWithoutCommentariesDTO toDTO(Book book);

  Book toBook(BookWithoutCommentariesDTO dto);
}
