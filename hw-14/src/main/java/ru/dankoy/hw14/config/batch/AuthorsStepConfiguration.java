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
import ru.dankoy.hw14.core.domain.sql.Author;
import ru.dankoy.hw14.core.processor.AuthorProcessor;
import ru.dankoy.hw14.core.repository.sql.AuthorSqlRepository;

@Configuration
public class AuthorsStepConfiguration {

  // миграция авторов
  @Bean
  public RepositoryItemReader<Author> authorsSqlReader(AuthorSqlRepository authorSqlRepository) {
    // позволяет работать с репозиторием напрямую. Работает с чанками сразу
    // Работает с датасурсом указанным в пропертях

    Map<String, Direction> sortMap = new HashMap<>();
    sortMap.put("id", Direction.ASC);

    return new RepositoryItemReaderBuilder<Author>()
        .repository(authorSqlRepository)
        .methodName("findAll")
        .sorts(sortMap)
        .saveState(false)
        .pageSize(10) //default
        .build();

  }

  @Bean
  public MongoItemWriter<ru.dankoy.hw14.core.domain.mongodb.Author> authorMongoWriter(
      MongoOperations mongoOperations) {

    String collection = "authors";

    return new MongoItemWriterBuilder<ru.dankoy.hw14.core.domain.mongodb.Author>()
        .template(mongoOperations)
        .collection(collection)
        .build();
  }

  @Bean
  public Step migrateAuthors(StepBuilderFactory stepBuilderFactory,
      ItemReader<Author> authorsSqlReader,
      ItemWriter<ru.dankoy.hw14.core.domain.mongodb.Author> authorMongoWriter,
      AuthorProcessor authorProcessor) {

    return stepBuilderFactory
        .get("step1")
        .<ru.dankoy.hw14.core.domain.sql.Author, ru.dankoy.hw14.core.domain.mongodb.Author>chunk(5)
        .reader(authorsSqlReader)
        .processor(authorProcessor)
        .writer(authorMongoWriter)
        .build();

  }

}
