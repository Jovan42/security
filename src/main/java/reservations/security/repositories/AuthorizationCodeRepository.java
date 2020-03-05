package reservations.security.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import reservations.security.domain.AuthorizationCodeEntity;
import reservations.security.domain.UserEntity;

public interface AuthorizationCodeRepository extends JpaRepository<AuthorizationCodeEntity, Long> {
    Optional<AuthorizationCodeEntity> findAuthorizationCodeEntityByUser(UserEntity user);
}
