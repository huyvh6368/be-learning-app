package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_level")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String describes;

    @OneToMany(mappedBy = "level")
    @JsonBackReference
    private List<Topic> topics;

    @OneToMany(mappedBy = "level")
    @JsonBackReference
    private List<LearnerLevel> learnerLevels;

}

