package reservations.security.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthCodeResponse {
    private String authCode;
    private Short state;
}
