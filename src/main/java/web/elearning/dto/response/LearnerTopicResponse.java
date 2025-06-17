package web.elearning.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LearnerTopicResponse {
    private Long id;
    private String learnerName;
    private Long learnerId;
    private String topicName;
    private Long topicId;
}
