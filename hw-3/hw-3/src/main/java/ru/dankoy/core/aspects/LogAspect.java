package ru.dankoy.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author turtality
 * <p>
 * Aspect testing.
 * <p>
 */
@Aspect
@Component
public class LogAspect {

  private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  @Around("@annotation(ru.dankoy.core.aspects.Log)")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

    logger.info("Before " + joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After " + joinPoint.getSignature());

    return res;

  }

}
