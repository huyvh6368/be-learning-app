package web.elearning.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {
    private String accountEmail;
    private Long accountId;
    private String accessToken;
    private String refreshToken;
}
