package ru.dankoy.hw19.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.domain.Note;
import ru.dankoy.hw19.core.domain.Shelf;
import ru.dankoy.hw19.core.domain.User;

/**
 * @author turtality
 * <p>
 * Aspect testing.
 * <p>
 */
@Aspect
@Component
public class UserAspect {

  // Для того что бы использовать с сервисами других классов отличных от комментариев, надо
  // добавить новый поинткат и указать другой тип аргумента
  @Around("@annotation(ru.dankoy.hw19.core.aspects.AddCurrentUser) && args(commentary)")
  public Object addCurrentUser(ProceedingJoinPoint joinPoint, Commentary commentary)
      throws Throwable {

    // Добавляет текущего юзера в объект комментария при создании или модификации комментария

    var logger = getLogger(joinPoint);
    var securityContextUser = getPrincipal();

    commentary.setUser(securityContextUser);

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

  @Around("@annotation(ru.dankoy.hw19.core.aspects.AddCurrentUser) && args(shelf)")
  public Object addCurrentUser(ProceedingJoinPoint joinPoint, Shelf shelf)
      throws Throwable {

    // Добавляет текущего юзера в объект комментария при создании или модификации комментария

    var logger = getLogger(joinPoint);

    var securityContextUser = getPrincipal();

    shelf.setUser(securityContextUser);

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

  @Around("@annotation(ru.dankoy.hw19.core.aspects.AddCurrentUser) && args(note)")
  public Object addCurrentUser(ProceedingJoinPoint joinPoint, Note note)
      throws Throwable {

    // Добавляет текущего юзера в объект комментария при создании или модификации комментария

    var logger = getLogger(joinPoint);
    var securityContextUser = getPrincipal();

    note.setUser(securityContextUser);

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

  private User getPrincipal() {
    return (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
  }

  private Logger getLogger(ProceedingJoinPoint joinPoint) {

    return LoggerFactory.getLogger(joinPoint.getTarget().getClass());

  }

}
