package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process, Long> {
}
