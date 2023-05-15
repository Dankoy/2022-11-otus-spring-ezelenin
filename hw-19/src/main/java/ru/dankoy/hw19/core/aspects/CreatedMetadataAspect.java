package ru.dankoy.hw19.core.aspects;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.domain.Publisher;
import ru.dankoy.hw19.core.domain.Work;
import ru.dankoy.hw19.core.service.commentary.CommentaryService;
import ru.dankoy.hw19.core.service.publisher.PublisherService;
import ru.dankoy.hw19.core.service.work.WorkService;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CreatedMetadataAspect {

  private final WorkService workService;
  private final CommentaryService commentaryService;
  private final PublisherService publisherService;

  @Before("@annotation(ru.dankoy.hw19.core.aspects.AddCreatedMetadata) && args(work)")
  public void addCreatedMetadata(Work work) {

    log.info("Aspect add meta to work");

    var found = workService.getById(work.getId());

    found.ifPresent(w -> {
      work.setCreatedByUser(w.getCreatedByUser());
      work.setDateCreated(w.getDateCreated());
    });

  }

  @Before("@annotation(ru.dankoy.hw19.core.aspects.AddCreatedMetadata) && args(publisher)")
  public void addCreatedMetadata(Publisher publisher) {

    log.info("Aspect add meta to publisher");

    var found = publisherService.findById(publisher.getId());

    found.ifPresent(w -> {
      publisher.setCreatedByUser(w.getCreatedByUser());
      publisher.setDateCreated(w.getDateCreated());
    });

  }

  @Before("@annotation(ru.dankoy.hw19.core.aspects.AddCreatedMetadata) && args(commentary)")
  public void addCreatedMetadata(Commentary commentary) {

    log.info("Aspect add meta to commentary");

    var found = commentaryService.getById(commentary.getId());

    found.ifPresent(w -> commentary.setDateCreated(w.getDateCreated()));

  }

}
