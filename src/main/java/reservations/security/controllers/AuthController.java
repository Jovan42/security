package reservations.security.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;
import reservations.security.domain.dtos.AuthTokenRequest;
import reservations.security.domain.dtos.AuthTokenResponse;
import reservations.security.domain.dtos.LoginRequest;
import reservations.security.services.AuthService;

@RestController
public class AuthController {

    private AuthService authService;

    @Value("${jwt.tokenSecret}")
    private String tokenSecret;

    @Value("${jwt.codeSecret}")
    private String codeSecret;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authCode")
    public ResponseEntity<AuthCodeResponse> getAuthorizationCode(
            @RequestBody AuthCodeRequest authCodeRequest) {
        return new ResponseEntity<>(
                authService.getAuthorizationCode(authCodeRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthCodeResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/token")
    public AuthTokenResponse exchangeAuthCode(@RequestBody AuthTokenRequest authTokenRequest) {
        return authService.exchangeAuthCode(authTokenRequest);
    }

    @PostMapping("/verify")
    public String verify(@RequestBody AuthTokenRequest authTokenRequest) {

        DecodedJWT verify =
                JWT.require(Algorithm.HMAC512(tokenSecret))
                        .build()
                        .verify(authTokenRequest.getState());
        return verify.getSubject();
    }
}
