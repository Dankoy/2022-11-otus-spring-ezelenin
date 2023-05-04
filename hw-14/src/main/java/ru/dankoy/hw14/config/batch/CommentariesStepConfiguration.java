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
import ru.dankoy.hw14.core.domain.sql.Commentary;
import ru.dankoy.hw14.core.processor.CommentaryProcessor;
import ru.dankoy.hw14.core.repository.sql.CommentarySqlRepository;

@Configuration
public class CommentariesStepConfiguration {

  // миграция комментариев

  @Bean
  public RepositoryItemReader<Commentary> commentariesSqlReader(
      CommentarySqlRepository commentarySqlRepository) {
    // позволяет работать с репозиторием напрямую. Работает с чанками сразу
    // Работает с датасурсом указанным в пропертях

    Map<String, Direction> sortMap = new HashMap<>();
    sortMap.put("id", Direction.ASC);

    return new RepositoryItemReaderBuilder<Commentary>()
        .repository(commentarySqlRepository)
        .methodName("findAll")
        .sorts(sortMap)
        .saveState(false)
        .pageSize(10) //default
        .build();

  }

  @Bean
  public MongoItemWriter<ru.dankoy.hw14.core.domain.mongodb.Commentary> commentariesMongoWriter(
      MongoOperations mongoOperations) {

    String collection = "commentaries";

    return new MongoItemWriterBuilder<ru.dankoy.hw14.core.domain.mongodb.Commentary>()
        .template(mongoOperations)
        .collection(collection)
        .build();
  }

  @Bean
  public Step migrateCommentaries(
      StepBuilderFactory stepBuilderFactory,
      ItemReader<Commentary> commentariesSqlReader,
      ItemWriter<ru.dankoy.hw14.core.domain.mongodb.Commentary> commentariesMongoWriter,
      CommentaryProcessor commentaryProcessor) {

    return stepBuilderFactory
        .get("step3")
        .<ru.dankoy.hw14.core.domain.sql.Commentary, ru.dankoy.hw14.core.domain.mongodb.Commentary>chunk(
            5)
        .reader(commentariesSqlReader)
        .processor(commentaryProcessor)
        .writer(commentariesMongoWriter)
        .build();

  }


}
