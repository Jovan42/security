package reservations.security.services;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reservations.security.domain.User;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;

@Service
public class AuthServiceImpl implements AuthService {
    public Optional<Boolean> checkUsernamePassword(User user) {
        return Optional.of(true);
    }

    @Override
    public AuthCodeResponse getAuthorizationCode(AuthCodeRequest authCodeRequest) {
        return new AuthCodeResponse(UUID.randomUUID().toString(), authCodeRequest.getState());
    }
}
