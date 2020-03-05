package reservations.security.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import reservations.security.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> getUserEntityByUsername(String username);
}
