package ru.dankoy.core.aspects;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author turtality
 * <p>
 * Пример аппендера. Например, можно создать аппендер как компонент, и зарегистрировать его в логбэк
 * в Main классе. Таким образом модно будет инжектить зависимости в компонент и писать в бд.
 *
 * Идая плохая, так как много заморочек.
 *
 */
public class LogbackSomeAppender extends AppenderBase<ILoggingEvent> {

  private static final Logger logger = LoggerFactory.getLogger(LogbackSomeAppender.class);

  @Override
  protected void append(ILoggingEvent iLoggingEvent) {
    logger.info(iLoggingEvent.getMarker() + " " + iLoggingEvent.getLoggerName());
  }
}
