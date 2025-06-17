package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcessResponse {
    private Long id;
    private String learnerName;
    private String learnerId;
    private String questionName;
    private String questionId;
    private String answerName;
    private String answerId;
    private BigDecimal score;
}

