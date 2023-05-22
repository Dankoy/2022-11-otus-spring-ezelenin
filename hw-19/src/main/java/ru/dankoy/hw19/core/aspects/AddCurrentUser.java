package ru.dankoy.hw19.core.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author turtality
 * <p>
 * Добавляет текущего юзера к объекту комментария
 */
@Deprecated(since = "2023-05-22", forRemoval = true)
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddCurrentUser {

}
