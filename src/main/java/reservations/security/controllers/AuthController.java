package reservations.security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reservations.security.domain.dtos.AuthCodeRequest;
import reservations.security.domain.dtos.AuthCodeResponse;
import reservations.security.domain.dtos.AuthTokenRequest;
import reservations.security.domain.dtos.AuthTokenResponse;
import reservations.security.domain.dtos.LoginRequest;
import reservations.security.domain.dtos.LoginResponse;
import reservations.security.services.AuthService;

@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/authCode")
    public ResponseEntity<AuthCodeResponse> getAuthorizationCode(
            @RequestBody AuthCodeRequest authCodeRequest) {
        return new ResponseEntity<>(
                new AuthCodeResponse(
                        authCodeRequest.getResponseType(),
                        "http://localhost:8888/login.html?" + authCodeRequest.getRedirectUrl(),
                        authCodeRequest.getState()),
                HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest loginRequest,
            @RequestParam(required = true) String redirectUrl) {
        return authService.login(
                loginRequest.getUsername(), loginRequest.getPassword(), redirectUrl);
    }

    @PostMapping("/token")
    public AuthTokenResponse exchangeAuthCode(@RequestBody AuthTokenRequest authTokenRequest) {
        return authService.exchangeAuthCode(authTokenRequest);
    }

    @PostMapping("/check")
    public String check() {
        return "asd";
    }
}
