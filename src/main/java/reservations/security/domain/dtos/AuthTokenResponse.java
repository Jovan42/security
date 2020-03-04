package reservations.security.domain.dtos;

public class AuthTokenResponse {
    private String token;
    private String tokenType;
    private Integer expiresIn;
    private String refresh_token;
}
