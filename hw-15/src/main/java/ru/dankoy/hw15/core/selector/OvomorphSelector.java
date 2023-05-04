package ru.dankoy.hw15.core.selector;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OvomorphSelector implements MessageSelector {

  @Override
  public boolean accept(Message<?> message) {

    var headers = message.getHeaders();
    var victimsAroundHeaderValue = headers.get("victimsAround");

    if (Objects.nonNull(victimsAroundHeaderValue)) {
      log.info("Victims will make new home for evolution of facehugger");
      return (boolean) victimsAroundHeaderValue;
    }
    log.info("No victims around. Facehugger will die.");
    return false;

  }
}
