package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResponse {
    private Long id;
    private LearnerResponse learner;
    private QuestionResponse question;
    private Integer score;
}

