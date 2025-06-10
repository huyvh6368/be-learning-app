package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// RankResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankResponse {
    private Long id;
    private String name;
    private String describes;
}
