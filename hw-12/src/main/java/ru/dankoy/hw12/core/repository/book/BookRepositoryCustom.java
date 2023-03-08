package ru.dankoy.hw12.core.repository.book;

import java.util.Optional;
import ru.dankoy.hw12.core.domain.Book;

public interface BookRepositoryCustom {

  Optional<Book> getById(long id);

}
