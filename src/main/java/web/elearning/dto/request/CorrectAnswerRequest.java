package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CorrectAnswerRequest {
    private String answerId;
    private String questionId;
    private String correctAnswer;
}
