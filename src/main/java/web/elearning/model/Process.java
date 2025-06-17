package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tbl_process")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "learner_id")
    @JsonManagedReference
    private Learner learner;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonManagedReference
    private Question question;


    @ManyToOne
    @JoinColumn(name = "answer_id")
    @JsonManagedReference
    private Answer answer;

    private BigDecimal score;
}


