package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
