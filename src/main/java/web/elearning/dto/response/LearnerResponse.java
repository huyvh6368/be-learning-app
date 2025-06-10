package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LearnerResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerResponse {
    private Long id;
    private String name;
    private Integer totalScore;
    private String code;
    private String rankName;
    private Long rankId;
    private AccountResponse account;
}
