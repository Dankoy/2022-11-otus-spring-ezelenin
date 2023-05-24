package ru.dankoy.hw19.core.service.userrole;


import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dankoy.hw19.core.domain.UserRole;
import ru.dankoy.hw19.core.repository.userrole.UserRoleRepository;


@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

  private final UserRoleRepository userRoleRepository;

  @Override
  public Optional<UserRole> findByRole(String role) {
    return userRoleRepository.findByRole(role);
  }
}
