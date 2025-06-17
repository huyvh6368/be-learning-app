package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_learner")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Learner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String urlImage;

    @ManyToOne
    @JoinColumn(name = "rank_id")
    @JsonManagedReference
    private Rank rank;

    @Column(name = "total_score")
    private BigDecimal totalScore;

    private String code;

    @OneToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "learner")
    @JsonBackReference
    private List<LearnerLevel> learnerLevels;

    @OneToMany(mappedBy = "learner")
    @JsonBackReference
    private List<Note> notes;

    @OneToMany(mappedBy = "learner")
    @JsonBackReference
    private List<Process> processes;
}


