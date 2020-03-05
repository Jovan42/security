package reservations.security.services;

import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;
import reservations.security.domain.dtos.AuthTokenRequest;
import reservations.security.domain.dtos.AuthTokenResponse;

public interface AuthService {

    AuthCodeResponse getAuthorizationCode(AuthCodeRequest authCodeRequest);

    AuthCodeResponse login(String username, String password);

    AuthTokenResponse exchangeAuthCode(AuthTokenRequest authTokenRequest);
}
