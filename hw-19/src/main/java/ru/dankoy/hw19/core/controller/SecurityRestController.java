package ru.dankoy.hw19.core.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dankoy.hw19.config.security.Authority;
import ru.dankoy.hw19.core.dto.user.UserRegisterDTO;
import ru.dankoy.hw19.core.exceptions.RegistrationException;
import ru.dankoy.hw19.core.service.user.UserService;
import ru.dankoy.hw19.core.service.userrole.UserRoleService;

@RequiredArgsConstructor
@RestController
public class SecurityRestController {

  private final UserService userService;
  private final UserRoleService userRoleService;

  private static final String DEFAULT_ROLE = "ROLE_" + Authority.OPERATOR;


  @PostMapping("/api/v1/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody UserRegisterDTO dto) {

    // сам добавляет дефолтную роль оператора для всех регистрируемых юзеров

    var roleOptional = userRoleService.findByRole(DEFAULT_ROLE);
    var role = roleOptional.orElseThrow(() -> new RegistrationException(
        String.format("Role '%s' has not been found", DEFAULT_ROLE)));

    var user = UserRegisterDTO.fromDTO(dto);
    user.addRole(role);

    userService.create(user);

  }

}
