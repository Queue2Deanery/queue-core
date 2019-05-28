package pl.ee.authentication.infrastructure.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.authentication.domain.model.db.RevokedJwtTokenEntity;

@Repository
public interface RevokedJwtTokenRepository extends JpaRepository<RevokedJwtTokenEntity, Long> {
  boolean existsByJwtToken(String jwtToken);
}
