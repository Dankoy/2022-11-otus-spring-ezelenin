package ru.dankoy.hw8.core.repository.commentary;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dankoy.hw8.core.domain.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long>,
    CommentaryRepositoryCustom {

  @Override
  Optional<Commentary> getById(long id);

  @Modifying
  @Query("delete from Commentary c where c.id = :id")
  void delete(@Param("id") long id);

}
