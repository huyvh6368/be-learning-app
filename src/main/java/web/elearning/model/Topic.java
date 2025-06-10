package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String describes;

    @ManyToOne
    @JoinColumn(name = "level_id")
    @JsonManagedReference
    private Level level;

    @OneToMany(mappedBy = "topic")
    @JsonBackReference
    private List<Question> questions;
}


