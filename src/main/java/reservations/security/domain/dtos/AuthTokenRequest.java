package reservations.security.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthTokenRequest {
    private String userName;
    private String authCode;
}
