package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_rank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private BigDecimal minScore;
    private BigDecimal maxScore;
    @Column(columnDefinition = "TEXT")
    private String describes;

    @OneToMany(mappedBy = "rank")
    @JsonBackReference
    private List<Learner> learners;
}


