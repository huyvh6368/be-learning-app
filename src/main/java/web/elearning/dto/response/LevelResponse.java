package web.elearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LevelResponse.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelResponse {
    private Long id;
    private String name;
    private String describes;
}
