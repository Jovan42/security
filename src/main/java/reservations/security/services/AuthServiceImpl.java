package reservations.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reservations.security.domain.AuthorizationCodeEntity;
import reservations.security.domain.UserEntity;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;
import reservations.security.domain.dtos.AuthTokenRequest;
import reservations.security.domain.dtos.AuthTokenResponse;
import reservations.security.repositories.AuthorizationCodeRepository;
import reservations.security.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${jwt.tokenSecret}")
    private String tokenSecret;

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

    @Override
    @Transactional
    public AuthTokenResponse exchangeAuthCode(AuthTokenRequest authTokenRequest) {
        String token = "";
        UserEntity userEntity =
                userRepository
                        .getUserEntityByUsername(authTokenRequest.getUsername())
                        .orElse(new UserEntity());

        AuthorizationCodeEntity authorizationCodeEntity =
                authorizationCodeRepository
                        .findAuthorizationCodeEntityByUser(userEntity)
                        .orElse(new AuthorizationCodeEntity());
        if (authorizationCodeEntity.getValue().equals(authTokenRequest.getCode())) {
            Date expires = new Date(System.currentTimeMillis() + 50000);
            token =
                    JWT.create()
                            .withSubject(authTokenRequest.getUsername())
                            .withExpiresAt(expires)
                            .sign(Algorithm.HMAC512(tokenSecret));
            authorizationCodeRepository.delete(authorizationCodeEntity);
        }
        return new AuthTokenResponse(token, "Bearer", 50000, null);
    }
}
