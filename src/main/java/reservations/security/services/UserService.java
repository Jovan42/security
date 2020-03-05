package reservations.security.services;

import org.springframework.stereotype.Service;
import reservations.security.domain.ScopeEntity;
import reservations.security.domain.UserEntity;

@Service
public interface UserService {
    UserEntity register(UserEntity userEntity);

    UserEntity addScope(ScopeEntity scopeEntity);
}
