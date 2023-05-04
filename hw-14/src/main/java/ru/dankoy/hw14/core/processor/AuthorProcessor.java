package ru.dankoy.hw14.core.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import ru.dankoy.hw14.core.domain.mongodb.Author;

@Component
public class AuthorProcessor implements
    ItemProcessor<ru.dankoy.hw14.core.domain.sql.Author, ru.dankoy.hw14.core.domain.mongodb.Author> {

  @Override
  public Author process(ru.dankoy.hw14.core.domain.sql.Author item) {
    return new Author(Long.toString(item.getId()), item.getName());
  }
}
