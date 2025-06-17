package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// LearnerResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnerResponse {
    private Long id;
    private String name;
    private BigDecimal totalScore;
    private String code;
    private String rankName;
    private Long rankId;
    private String accountName;
    private Long accountId;
}
