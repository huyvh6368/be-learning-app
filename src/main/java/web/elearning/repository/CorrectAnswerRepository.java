package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.CorrectAnswer;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, Long> {
}
