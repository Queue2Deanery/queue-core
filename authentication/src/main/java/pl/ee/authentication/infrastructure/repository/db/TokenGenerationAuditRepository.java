package pl.ee.authentication.infrastructure.repository.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ee.authentication.domain.model.db.TokenGenerationAuditEntity;

import java.util.List;

@Repository
public interface TokenGenerationAuditRepository extends JpaRepository<TokenGenerationAuditEntity, Long> {
  List<TokenGenerationAuditEntity> findAllByUserIndex(String userIndex);
}
