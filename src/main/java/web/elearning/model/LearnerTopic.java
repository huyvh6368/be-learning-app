package web.elearning.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_learner_topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "learner_id")
    private Learner learner;

    @OneToOne
    @JoinColumn(name = "learner_id")
    private Topic topic;
}
