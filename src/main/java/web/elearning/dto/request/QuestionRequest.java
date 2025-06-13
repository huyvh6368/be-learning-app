package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String code;
    private String title;
    private String imgUrl;
    private String describes;
    private String score;
    private String topicId;
}

