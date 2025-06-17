package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.LearnerLevel;

@Repository
public interface LearnerLevelRepository extends JpaRepository<LearnerLevel, Long> {
    boolean existsByLearnerIdAndLevelId(Long learnerId, Long levelId);
}
