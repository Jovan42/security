package reservations.security.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import java.util.Date;
import java.util.UUID;
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
import reservations.security.services.AuthService;

@RestController
public class AuthController {

    private AuthService authService;

    @Value("jwt.secret=")
    private String secret;
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

    @PostMapping("/token")
    public String exchangeAuthCode(@RequestBody AuthTokenRequest authTokenRequest) {
        System.out.println(secret);
        String token =
                JWT.create()
                        .withSubject("sasd")
                        .withExpiresAt(new Date(System.currentTimeMillis() + 50000))
                        .sign(Algorithm.HMAC512(secret));

        DecodedJWT verify = JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
        return token;
    }

    @PostMapping("/verify")
    public String verify(@RequestBody AuthTokenRequest authTokenRequest) {

        DecodedJWT verify = JWT.require(Algorithm.HMAC512(secret)).build().verify(authTokenRequest.getAuthCode());
        return verify.getSubject();
    }
}
