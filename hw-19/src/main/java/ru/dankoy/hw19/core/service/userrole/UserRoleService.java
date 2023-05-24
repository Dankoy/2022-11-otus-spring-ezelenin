package ru.dankoy.hw19.core.service.userrole;

import java.util.Optional;
import ru.dankoy.hw19.core.domain.UserRole;

public interface UserRoleService {

  Optional<UserRole> findByRole(String role);

}
