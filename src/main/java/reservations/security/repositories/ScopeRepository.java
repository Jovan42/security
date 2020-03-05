package reservations.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import reservations.security.domain.ScopeEntity;

public interface ScopeRepository extends JpaRepository<ScopeEntity, Long> {}
