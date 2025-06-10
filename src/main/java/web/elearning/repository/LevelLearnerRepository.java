package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.LevelLearner;

@Repository
public interface LevelLearnerRepository extends JpaRepository<LevelLearner, Long> {
}
