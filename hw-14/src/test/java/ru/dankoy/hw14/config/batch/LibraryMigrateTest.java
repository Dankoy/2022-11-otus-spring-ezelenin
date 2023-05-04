package ru.dankoy.hw14.config.batch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dankoy.hw14.core.domain.mongodb.Author;
import ru.dankoy.hw14.core.domain.mongodb.Book;
import ru.dankoy.hw14.core.domain.mongodb.Commentary;
import ru.dankoy.hw14.core.domain.mongodb.Genre;
import ru.dankoy.hw14.core.repository.mongodb.BookMongoRepository;
import ru.dankoy.hw14.core.repository.mongodb.CommentaryMongoRepository;

@DisplayName("Library migration job test ")
@SpringBootTest
@SpringBatchTest
//@Import(value = {BookMongoRepository.class, CommentaryMongoRepository.class})
public class LibraryMigrateTest {

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired
  private JobRepositoryTestUtils jobRepositoryTestUtils;


  private static final String JOB_NAME = "migrateLibraryJob";


  @Autowired
  private BookMongoRepository bookMongoRepository;


  @Autowired
  private CommentaryMongoRepository commentaryMongoRepository;

  @BeforeEach
  void clearMetaData() {
    jobRepositoryTestUtils.removeJobExecutions();
  }


  @DisplayName("test job")
  @Test
  void testMigrateLibraryFromSqlH2DbToMongoDb() throws Exception {

    var correctBooks = makeCorrectAllBooksList();
    var correctBooksList = new HashSet<>(correctBooks.values());

    Map<String, List<Commentary>> expectedCommentaries = makeCorrectCommentaryList();
    Map<String, List<Commentary>> commentariesMergedWithBook = mergeCommentariesAndBooks(
        expectedCommentaries, correctBooks);

    var expectedBook1Commentaries = commentariesMergedWithBook.get("book1");
    var expectedBook2Commentaries = commentariesMergedWithBook.get("book2");

    Job job = jobLauncherTestUtils.getJob();
    assertThat(job).isNotNull()
        .extracting(Job::getName)
        .isEqualTo(JOB_NAME);

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    var actualBooks = bookMongoRepository.findAll();
    var actualBook1Commentaries = commentaryMongoRepository.findByBookId("1");
    var actualBook2Commentaries = commentaryMongoRepository.findByBookId("2");
    var actualBook3Commentaries = commentaryMongoRepository.findByBookId("3");

    assertThat(jobExecution.getExitStatus().getExitCode()).isEqualTo("COMPLETED");
    assertThat(actualBooks).hasSameElementsAs(correctBooksList);
    assertThat(actualBook1Commentaries).hasSameElementsAs(expectedBook1Commentaries);
    assertThat(actualBook2Commentaries).hasSameElementsAs(expectedBook2Commentaries);
    assertThat(actualBook3Commentaries).isEmpty();

  }


  private Map<String, Book> makeCorrectAllBooksList() {

    var book1 = new Book("1", "book1",
        Set.of(new Author("1", "author1"), new Author("2", "author2")),
        Set.of(new Genre("genre1"), new Genre("genre2")));

    var book2 = new Book("2", "book2",
        Set.of(new Author("2", "author2"), new Author("3", "author3")),
        Set.of(new Genre("genre2"), new Genre("genre3")));

    var book3 = new Book("3", "book3",
        Set.of(new Author("1", "author1"), new Author("3", "author3")),
        Set.of(new Genre("genre1"), new Genre("genre3")));

    return Map.of("book1", book1,
        "book2", book2,
        "book3", book3
    );
  }


  private Map<String, List<Commentary>> makeCorrectCommentaryList() {

    var book1Commentaries = List.of(new Commentary("1", "com1", null),
        new Commentary("2", "com2", null),
        new Commentary("3", "com3", null));

    var book2Commentaries = List.of(new Commentary("4", "com4", null),
        new Commentary("5", "com5", null),
        new Commentary("6", "com6", null));

    return Map.of("book1", book1Commentaries,
        "book2", book2Commentaries);

  }

  private Map<String, List<Commentary>> mergeCommentariesAndBooks(
      Map<String, List<Commentary>> commentaries,
      Map<String, Book> books) {

    Map<String, List<Commentary>> newList = new HashMap<>();

    for (Map.Entry<String, List<Commentary>> entry : commentaries.entrySet()) {

      var book = books.get(entry.getKey());

      List<Commentary> newCommentaryList = entry.getValue().stream()
          .map(c -> new Commentary(c.getId(), c.getText(), book)).collect(Collectors.toList());

      newList.put(entry.getKey(), newCommentaryList);

    }

    return newList;


  }


}
