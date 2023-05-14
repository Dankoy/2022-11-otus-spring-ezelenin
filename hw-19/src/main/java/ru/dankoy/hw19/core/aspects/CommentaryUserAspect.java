package ru.dankoy.hw19.core.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.dankoy.hw19.core.domain.Commentary;
import ru.dankoy.hw19.core.domain.User;

/**
 * @author turtality
 * <p>
 * Aspect testing.
 * <p>
 */
@Aspect
@Component
public class CommentaryUserAspect {

  @Around("@annotation(ru.dankoy.hw19.core.aspects.CommentaryCurrentUser) && args(commentary)")
  public Object addCurrentUserToCommentary(ProceedingJoinPoint joinPoint, Commentary commentary)
      throws Throwable {

    // Добавляет текущего юзера в объект комментария при создании или модификации комментария

    var logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

    var securityContextUser = (User) SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();

    commentary.setUser(securityContextUser);

    logger.info("Before {}", joinPoint.getSignature());

    var res = joinPoint.proceed();

    logger.info("After {}", joinPoint.getSignature());

    return res;

  }

}
