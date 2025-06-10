package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String describes;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    @JsonManagedReference
    private Topic topic;

    @OneToMany(mappedBy = "question")
    @JsonBackReference
    private List<Answer> answers;

    @OneToOne
    @JoinColumn(name = "correct_answer_id")
    private Answer correctAnswer;

    @OneToMany(mappedBy = "question")
    @JsonBackReference
    private List<Note> notes;

    @OneToMany(mappedBy = "question")
    @JsonBackReference
    private List<Process> processes;

    @ManyToOne
    @JsonManagedReference
    private Level level;
}


