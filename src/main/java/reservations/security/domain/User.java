package reservations.security.domain;

import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private Set<UserScope> scopes;
}
