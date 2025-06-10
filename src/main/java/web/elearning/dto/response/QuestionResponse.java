package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private Long id;
    private String code;
    private String title;
    private String imgUrl;
    private String describes;
    private Integer score;
    private String topicName;
    private Long topicId;
    private Long answerId;
    private String answerName;
}
