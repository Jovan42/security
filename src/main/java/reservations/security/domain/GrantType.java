package reservations.security.domain;

public enum GrantType {
    AUTHORIZATION_CODE("authorization_code"),
    IMPLICIT("implicit"),
    RESOURCE_OWNER("resource_owner"),
    CLIENT_CREDENTIALS("client_credentials");

    String value;

    GrantType(String value) {
        this.value = value;
    }
}
