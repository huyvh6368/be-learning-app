package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// RankResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankResponse {
    private Long id;
    private String name;
    private BigDecimal score;
    private String describes;
}
