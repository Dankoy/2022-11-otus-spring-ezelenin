package ru.dankoy.hw19.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;


// секурити через куки достигается за счет стандартного логина от спринга7
@Configuration
@OpenAPIDefinition(info = @Info(title = "Books catalogue API", version = "2.0", description = "Books catalogue"))
public class OpenApi {

}
