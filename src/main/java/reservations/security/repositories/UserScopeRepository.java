package reservations.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reservations.security.domain.UserScopeEntity;

public interface UserScopeRepository extends JpaRepository<UserScopeEntity, Long> {}
