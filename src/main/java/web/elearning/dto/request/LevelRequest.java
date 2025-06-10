package web.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// LevelRequest.java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LevelRequest {
    private String name;
    private String describes;
}
