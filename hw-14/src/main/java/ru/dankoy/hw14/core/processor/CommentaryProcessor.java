package ru.dankoy.hw14.core.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw14.core.domain.mongodb.Book;
import ru.dankoy.hw14.core.domain.mongodb.Commentary;

@Component
public class CommentaryProcessor implements
    ItemProcessor<ru.dankoy.hw14.core.domain.sql.Commentary, Commentary> {

  @Override
  public Commentary process(ru.dankoy.hw14.core.domain.sql.Commentary item) {

    var sqlBook = item.getBook();
    var nosqlBook = new Book(Long.toString(sqlBook.getId()), null, null, null);

    return new Commentary(Long.toString(item.getId()), item.getText(), nosqlBook);
  }
}
