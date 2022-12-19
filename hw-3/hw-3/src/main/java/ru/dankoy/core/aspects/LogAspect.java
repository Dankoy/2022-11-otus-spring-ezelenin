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

  @Around("@annotation(ru.dankoy.core.aspects.Log)")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

    // здесь получаем класс поинтката, но тратим ресурсы на создание объекта логгера.
    // Этот вариант лучше другого
    var logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

  private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

  @Around("@annotation(ru.dankoy.core.aspects.Log)")
  public Object log2(ProceedingJoinPoint joinPoint) throws Throwable {

    // здесь используем логгер аспекта, то есть в логах увидим класс аспекта, а не класс поинтката

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

}
