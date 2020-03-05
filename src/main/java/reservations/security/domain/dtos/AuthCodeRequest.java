package reservations.security.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthCodeRequest {
    private String userName;
    private String password;
    //    private Scope scope;
    private Short state;;
}
