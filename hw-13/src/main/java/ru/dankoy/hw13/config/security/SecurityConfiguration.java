package ru.dankoy.hw13.config.security;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // hasAnyAuthority - позволяет работать с ролями без префикса ROLE_
    // hasAnyRole - в бд обязательно должны быть роли с префиксом ROLE_

    http
        .csrf().disable()
        .authorizeHttpRequests(authorize ->
                authorize
                    .antMatchers("/books")
                    .hasAnyRole(Authority.ADMIN.name(), Authority.OPERATOR.name(),
                        Authority.READER.name()) // для любой роли доступен.

                    .antMatchers("/book/create")
                    .hasAnyRole(Authority.ADMIN.name(), Authority.OPERATOR.name())

                    .antMatchers(HttpMethod.GET,
                        "/book/edit") // для примера, что есть возможность управлять методами
                    .hasAnyRole(Authority.ADMIN.name(), Authority.OPERATOR.name(),
                        Authority.READER.name())

                    .antMatchers(HttpMethod.POST, "/book/edit")
                    .hasAnyRole(Authority.ADMIN.name(), Authority.OPERATOR.name())

                    .antMatchers("/book/delete")
                    .hasAnyRole(Authority.ADMIN.name())

                    .anyRequest().authenticated()
            // обязательно любой запрос должен быть аутентифицирован

        )
        .formLogin();

    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    // Генерирует сам соль и хранит в той же строке, что и пароль в бд, самим не надо ничего делать
    // Если не добавить, то ошибка - There is no PasswordEncoder mapped for the id "null"
    return new BCryptPasswordEncoder(10);
  }

}
