package reservations.security.services;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservations.security.domain.AuthorizationCodeEntity;
import reservations.security.domain.UserEntity;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;
import reservations.security.repositories.AuthorizationCodeRepository;
import reservations.security.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private AuthorizationCodeRepository authorizationCodeRepository;

    @Autowired
    public AuthServiceImpl(
            BCryptPasswordEncoder bCryptPasswordEncoder,
            UserRepository userRepository,
            AuthorizationCodeRepository authorizationCodeRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.authorizationCodeRepository = authorizationCodeRepository;
    }

    @Override
    public AuthCodeResponse getAuthorizationCode(AuthCodeRequest authCodeRequest) {
        return new AuthCodeResponse(UUID.randomUUID().toString(), authCodeRequest.getState());
    }

    @Override
    @Transactional
    public AuthCodeResponse login(String username, String password) {
        UserEntity userEntity =
                userRepository.getUserEntityByUsername(username).orElse(new UserEntity());
        AuthorizationCodeEntity authorizationCodeEntity = new AuthorizationCodeEntity();

        authorizationCodeRepository
                .findAuthorizationCodeEntityByUser(userEntity)
                .ifPresent(
                        code -> {
                            authorizationCodeRepository.delete(code);
                        });

        if (bCryptPasswordEncoder.matches(password, userEntity.getPassword())) {
            String code = UUID.randomUUID().toString();
            authorizationCodeEntity.setExpires(LocalDateTime.now().plusMinutes(5));
            authorizationCodeEntity.setValue(code);
            authorizationCodeEntity.setUser(userEntity);
            authorizationCodeRepository.save(authorizationCodeEntity);
        }
        return new AuthCodeResponse(authorizationCodeEntity.getValue(), null);
    }
}
