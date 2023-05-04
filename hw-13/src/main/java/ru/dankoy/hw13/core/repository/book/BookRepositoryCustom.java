package ru.dankoy.hw13.core.repository.book;

import java.util.Optional;
import ru.dankoy.hw13.core.domain.Book;

public interface BookRepositoryCustom {

  Optional<Book> getById(long id);

}
