package reservations.security.services;

import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;

public interface AuthService {

    AuthCodeResponse getAuthorizationCode(AuthCodeRequest authCodeRequest);

    AuthCodeResponse login(String username, String password);
}
