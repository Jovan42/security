package reservations.security.services;

import java.util.Optional;
import reservations.security.domain.User;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;

public interface AuthService {
    Optional<Boolean> checkUsernamePassword(User user);

    AuthCodeResponse getAuthorizationCode(AuthCodeRequest authCodeRequest);
}
