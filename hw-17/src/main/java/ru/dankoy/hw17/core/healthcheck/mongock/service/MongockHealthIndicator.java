package ru.dankoy.hw17.core.healthcheck.mongock.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;
import ru.dankoy.hw17.core.healthcheck.mongock.domain.MongockChangeLog;
import ru.dankoy.hw17.core.healthcheck.mongock.repository.MongockChangeLogsRepository;

@Service
@RequiredArgsConstructor
public class MongockHealthIndicator implements HealthIndicator {

  private final MongockChangeLogsRepository mongockChangeLogsRepository;
  private static final String SUCCESS_STATE = "EXECUTED";

  @Override
  public Health health() {

    var list = getList();

    long countErrors = list.stream()
        .filter(l -> !l.getState().equals(SUCCESS_STATE))
        .count();

    if (countErrors > 0) {
      return Health
          .down()
          .withDetail("mongock", "failed")
          .withDetail("logs", list)
          .build();
    }

    return Health
        .up()
        .withDetail("mongock", "success")
        .withDetail("logs", list)
        .build();
  }

  private List<MongockChangeLog> getList() {
    return mongockChangeLogsRepository.findAll();
  }
}
