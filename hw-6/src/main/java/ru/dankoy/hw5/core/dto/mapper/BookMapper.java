package ru.dankoy.hw5.core.dto.mapper;

import ru.dankoy.hw5.core.domain.Book;
import ru.dankoy.hw5.core.dto.BookWithoutCommentariesDTO;

public interface BookMapper {

  BookWithoutCommentariesDTO toDTO(Book book);

  Book toBook(BookWithoutCommentariesDTO dto);
}
