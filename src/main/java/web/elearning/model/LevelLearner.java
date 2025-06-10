package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_level_learner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelLearner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    @JsonManagedReference
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonManagedReference
    private Level level;

    private String status;
}


