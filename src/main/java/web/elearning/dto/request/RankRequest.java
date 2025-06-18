package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// RankRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankRequest {
    private String name;
    private BigDecimal score;
    private String describes;
}
