package reservations.security.services;

import reservations.security.domain.dtos.AuthTokenRequest;
import reservations.security.domain.dtos.AuthTokenResponse;
import reservations.security.domain.dtos.LoginResponse;

public interface AuthService {

    LoginResponse login(String username, String password, String redirectUrl);

    AuthTokenResponse exchangeAuthCode(AuthTokenRequest authTokenRequest);
}
