package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerLevelResponse {
    private Long id;
    private String status;
    private String learnerName;
    private Long learnerId;
    private String levelName;
    private Long levelId;
}

