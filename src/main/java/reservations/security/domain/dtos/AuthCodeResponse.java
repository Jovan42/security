package reservations.security.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthCodeResponse {
    private String responseType;
    private String url;
    private String state;
}
