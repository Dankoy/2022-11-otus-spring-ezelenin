package ru.dankoy.hw14.core.commands;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.dankoy.hw14.core.repository.mongodb.BookMongoRepository;
import ru.dankoy.hw14.core.repository.mongodb.CommentaryMongoRepository;

@Slf4j
@RequiredArgsConstructor
@ShellComponent
public class MigrateBookCommand {

  private final Job importUserJob;

  private final JobLauncher jobLauncher;
  private final JobOperator jobOperator;
  private final JobExplorer jobExplorer;

  private final BookMongoRepository bookMongoRepository;
  private final CommentaryMongoRepository commentaryMongoRepository;
  private final ObjectMapper objectMapper;

  @ShellMethod(value = "start-migration-with-job-launcher", key = "sm-jl")
  public void startMigrationJobWithJobLauncher() throws Exception {
    JobExecution execution = jobLauncher.run(importUserJob, new JobParametersBuilder()
        .toJobParameters());
    log.info("Execution - {}", execution);
  }


  @ShellMethod(value = "get-books-from-mongo", key = "gb")
  public String getBooksFromMongo() throws JsonProcessingException {

    var books = bookMongoRepository.findAll();

    return objectMapper.writeValueAsString(books);
  }


  @ShellMethod(value = "getBooksFromMongo", key = "gc")
  public String getCommentariesFromMongo(@ShellOption String id) throws JsonProcessingException {

    var books = commentaryMongoRepository.findByBookId(id);

    return objectMapper.writeValueAsString(books);
  }


}
