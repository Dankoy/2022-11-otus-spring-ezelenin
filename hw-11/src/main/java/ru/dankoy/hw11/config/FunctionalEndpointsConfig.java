package ru.dankoy.hw11.config;


import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import ru.dankoy.hw11.core.service.commentary.CommentaryService;

@Configuration
public class FunctionalEndpointsConfig {

  @Bean
  public RouterFunction<ServerResponse> composedRoutes(CommentaryService service) {
    return route()
        // эта функция должна стоять раньше findAll - порядок следования роутов - важен
        .GET("/func/person",
            queryParam("name", StringUtils::isNotEmpty),
            request -> request.queryParam("name")
                .map(repository::findAllByLastName)
                .map(person -> ok().body(person, Person.class))
                .orElse(badRequest().build())
        )
        // пример другой реализации - начиная с запроса репозитория
        .GET("/func/person", queryParam("age", StringUtils::isNotEmpty),
            req ->
                repository
                    .findAllByAge(req.queryParam("age").map(Integer::parseInt)
                        .orElseThrow(IllegalArgumentException::new))
                    .collectList()
                    .transform(persons -> ok().contentType(APPLICATION_JSON).body(persons, Person.class))
        )
        // Обратите внимание на использование хэндлера
        .GET("/func/person", accept(APPLICATION_JSON), new PersonHandler(repository)::list)
        // Обратите внимание на использование pathVariable
        .GET("/func/person/{id}", accept(APPLICATION_JSON),
            request -> repository.findById(request.pathVariable("id"))
                .flatMap(person -> ok().contentType(APPLICATION_JSON).body(fromValue(person)))
                .switchIfEmpty(notFound().build())
        ).build();
  }


}
