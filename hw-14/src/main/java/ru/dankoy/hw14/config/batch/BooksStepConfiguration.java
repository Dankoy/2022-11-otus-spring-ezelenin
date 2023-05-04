package ru.dankoy.hw14.config.batch;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import ru.dankoy.hw14.core.domain.sql.Book;
import ru.dankoy.hw14.core.processor.BookProcessor;
import ru.dankoy.hw14.core.repository.sql.BookSqlRepository;

@Configuration
public class BooksStepConfiguration {


  // миграция книг
  @Bean
  public RepositoryItemReader<Book> booksSqlReader(BookSqlRepository bookSqlRepository) {
    // позволяет работать с репозиторием напрямую. Работает с чанками сразу
    // Работает с датасурсом указанным в пропертях

    Map<String, Direction> sortMap = new HashMap<>();
    sortMap.put("id", Direction.ASC);

    return new RepositoryItemReaderBuilder<Book>()
        .repository(bookSqlRepository)
        .methodName("findAll")
        .sorts(sortMap)
        .saveState(false)
        .pageSize(10) //default
        .build();

  }

  @Bean
  public MongoItemWriter<ru.dankoy.hw14.core.domain.mongodb.Book> booksMongoWriter(
      MongoOperations mongoOperations) {

    String collection = "books";

    return new MongoItemWriterBuilder<ru.dankoy.hw14.core.domain.mongodb.Book>()
        .template(mongoOperations)
        .collection(collection)
        .build();
  }


  @Bean
  public Step migrateBooks(StepBuilderFactory stepBuilderFactory, ItemReader<Book> booksSqlReader,
      ItemWriter<ru.dankoy.hw14.core.domain.mongodb.Book> booksMongoWriter,
      BookProcessor bookProcessor) {

    return stepBuilderFactory
        .get("step2")
        .<ru.dankoy.hw14.core.domain.sql.Book, ru.dankoy.hw14.core.domain.mongodb.Book>chunk(5)
        .reader(booksSqlReader)
        .processor(bookProcessor)
        .writer(booksMongoWriter)
        .build();

  }
}
