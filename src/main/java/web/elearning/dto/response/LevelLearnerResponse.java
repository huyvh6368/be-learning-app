package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelLearnerResponse {
    private Long id;
    private String status;
    private LearnerResponse learner;
    private LevelResponse level;
}

