package ru.dankoy.hw14.config.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class JobConfig {

  @Bean
  public Job migrateLibraryJob(JobBuilderFactory jobBuilderFactory, Step migrateAuthors,
      Step migrateBooks, Step migrateCommentaries) {
    return jobBuilderFactory.get("migrateLibraryJob")
        .incrementer(new RunIdIncrementer())
        .flow(migrateAuthors)
        .next(migrateBooks)
        .next(migrateCommentaries)
        .end()
        .build();
  }


}
