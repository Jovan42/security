package reservations.security.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthTokenResponse {
    private String token;
    private String tokenType;
    private Integer expiresIn;
    private String refresh_token;
}
