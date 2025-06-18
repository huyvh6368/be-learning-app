package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.elearning.model.LearnerLevel;

import java.util.List;

@Repository
public interface LearnerLevelRepository extends JpaRepository<LearnerLevel, Long> {
    boolean existsByLearnerIdAndLevelId(Long learnerId, Long levelId);

    @Query("SELECT ll FROM LearnerLevel ll JOIN FETCH ll.level WHERE ll.learner.id = :learnerId")
    List<LearnerLevel> findAllWithLevelByLearnerId(@Param("learnerId") Long learnerId);
}
