package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.elearning.model.LearnerTopic;

import java.util.List;

@Repository
public interface LearnerTopicRepository extends JpaRepository<web.elearning.model.LearnerTopic, Long> {
    Boolean existsByLearnerIdAndTopicId(Long learnerId, Long topicId);

    @Query("SELECT lt FROM LearnerTopic lt JOIN FETCH lt.topic WHERE lt.learner.id = :learnerId")
    List<LearnerTopic> findAllWithTopicByLearnerId(@Param("learnerId") Long learnerId);
}
