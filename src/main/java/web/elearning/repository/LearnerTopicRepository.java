package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnerTopicRepository extends JpaRepository<web.elearning.model.LearnerTopic, Long> {
    Boolean existsByLearnerIdAndTopicId(Long learnerId, Long topicId);
}
