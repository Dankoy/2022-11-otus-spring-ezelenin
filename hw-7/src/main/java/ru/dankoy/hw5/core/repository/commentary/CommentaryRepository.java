package ru.dankoy.hw5.core.repository.commentary;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.dankoy.hw5.core.domain.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long>, CommentaryRepositoryCustom {

  Optional<Commentary> getById(long id);

  Commentary save(Commentary commentary);

  @Modifying
  @Query("delete from Commentary c where c.id = :id")
  void delete(@Param("id") long id);

}
