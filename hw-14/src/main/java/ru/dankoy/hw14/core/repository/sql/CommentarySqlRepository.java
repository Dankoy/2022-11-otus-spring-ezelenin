package ru.dankoy.hw14.core.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dankoy.hw14.core.domain.sql.Commentary;

public interface CommentarySqlRepository extends JpaRepository<Commentary, Long> {

}
