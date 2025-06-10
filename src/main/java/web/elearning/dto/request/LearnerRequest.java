package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LearnerRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerRequest {
    private String name;
    private Long rankId;
    private Integer totalScore;
    private String urlImage;
    private String code;
    private Long accountId;
}

