package reservations.security.domain.dtos;

import java.util.Set;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import reservations.security.domain.GrantType;

@Getter
@Setter
public class AuthTokenRequest {
    @NotEmpty private GrantType grantType;
    private Long clientId;
    private String redirectUrl;
    private Set<String> scope;
    private String state;
}
