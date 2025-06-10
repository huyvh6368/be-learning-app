package web.elearning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.elearning.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
