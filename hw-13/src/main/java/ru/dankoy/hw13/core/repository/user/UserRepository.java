package ru.dankoy.hw13.core.repository.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw13.core.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

}
