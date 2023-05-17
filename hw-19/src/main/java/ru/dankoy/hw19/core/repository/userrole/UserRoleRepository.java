package ru.dankoy.hw19.core.repository.userrole;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.dankoy.hw19.core.domain.UserRole;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

  Optional<UserRole> findByRole(String role);

}
