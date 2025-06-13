package web.elearning.dto.request;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnswerRequest {
    private String name;
    private Long questionId;
    private Boolean correct;
}

