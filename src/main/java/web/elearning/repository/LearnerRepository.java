package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.Learner;

@Repository
public interface LearnerRepository extends JpaRepository<Learner, Long> {
}
