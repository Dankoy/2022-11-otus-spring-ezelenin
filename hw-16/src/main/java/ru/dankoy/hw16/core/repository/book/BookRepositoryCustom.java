package ru.dankoy.hw16.core.repository.book;

import java.util.Optional;
import ru.dankoy.hw16.core.domain.Book;

public interface BookRepositoryCustom {

  Optional<Book> getById(long id);

}
