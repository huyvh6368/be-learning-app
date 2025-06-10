package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelLearnerRequest {
    private Long learnerId;
    private Long levelId;
    private String status;
}

