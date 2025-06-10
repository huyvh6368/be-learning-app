package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.Level;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
}
